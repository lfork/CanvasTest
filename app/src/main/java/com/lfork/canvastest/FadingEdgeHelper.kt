package com.lfork.canvastest

import android.content.Context
import android.graphics.*
import android.util.Log

internal class FadingEdgeHelper(context: Context?) {

    private val TAG = "FadingEdgeHelper"
    var fadingEdgeLength = 100
    private val paint: Paint = Paint()
    private val matrix: Matrix = Matrix()
    private val shader: Shader
    private val mCirclePaint: Paint= Paint(Paint.ANTI_ALIAS_FLAG)

    private val circleFadingPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var saveCount = 0


    init {


        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        shader = LinearGradient(
            0f, 0f, 0f, 1f, Color.BLACK, Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader


        mCirclePaint.color = Color.RED


        circleFadingPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    }

    fun saveCanvasLayer(canvas: Canvas, width: Int, height: Int) {
        saveCount = canvas.saveCount

        // 调用这个方法后,画布的新layer区域就是RectF限定的区域
        // todo 暂时不知道为啥进行xferMode处理前需要开启一个新的layer，不然在初始canvas所在的layer的合成处理的效果就会有问题。
        canvas.saveLayerAlpha(RectF(0f, 0f, width.toFloat(), height.toFloat()), 255)
        canvas.drawColor(Color.GREEN)

        // val rectRight = RectF((width - fadingEdgeLength).toFloat(), 0f, width.toFloat(), height.toFloat())
        // 调用这个方法后,画布的区域就是rectRight限定的区域
        // canvas.saveLayerAlpha(rectRight, 255)
        // canvas.drawColor(Color.BLUE)
        Log.d(TAG, "saveCanvasLayer() called with: saveCount = $saveCount")
    }

    /**
     * save的状态是通过栈管理的，调用restoreToCount会进行出栈操作，恢复到指定的栈位置
     * 如果[saveCount]是在save之前的值，一般是1， 那么调用restoreToCount(1)就会把canvas恢复到初始的状态
     */
    fun restoreCanvas(canvas: Canvas) {
        canvas.restoreToCount(saveCount)
    }

    fun drawHorizontal(canvas: Canvas, width: Int, height: Int) {



        // matrix.setScale(1f, fadingEdgeLength.toFloat())
        // matrix.postRotate(-90f)
        // matrix.postTranslate(0f, 0f)
        // shader.setLocalMatrix(matrix)
        // paint.shader = shader
        // val leftRectF = RectF(0f, 0f, fadingEdgeLength.toFloat(), height.toFloat())
        // Log.d(TAG, "drawHorizontal() called with: rightRectF = $leftRectF, width = $width, height = $height")
        // canvas.drawRect(leftRectF, paint)

        matrix.setScale(1f, fadingEdgeLength.toFloat())
        matrix.postRotate(90f)
        matrix.postTranslate(width.toFloat(), 0f)
        shader.setLocalMatrix(matrix)
        paint.shader = shader
        val rightRectF = RectF((width - fadingEdgeLength).toFloat(), 0f, width.toFloat(), height.toFloat())
        Log.d(TAG, "drawHorizontal() called with: rightRectF = $rightRectF, width = $width, height = $height")
        canvas.drawRect(rightRectF, paint)


        val radius = 300
        // canvas.drawCircle(0f, 0f, radius.toFloat(), mCirclePaint)
        circleFadingPaint.shader = RadialGradient(0f, 0f,  fadingEdgeLength.toFloat(), Color.TRANSPARENT, Color.BLACK,
            Shader.TileMode.CLAMP)
        canvas.drawPaint(circleFadingPaint)
    }
}