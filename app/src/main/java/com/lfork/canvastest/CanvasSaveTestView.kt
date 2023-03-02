package com.lfork.canvastest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CanvasSaveTestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "CanvasSaveTestView"
    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }
    private val rect = Rect(10, 10, 400, 400)

    // 重写该方法，进行绘图
    @SuppressLint("UseCompatLoadingForDrawables", "SuspiciousIndentation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw1,
        // drawTest(canvas, needSave = true, needRestore = true)

        // draw2 单独调用draw2和draw3从外表看画出来的内容是一样的
        // drawTest(canvas, needSave = false)


        // draw3
        drawTest(canvas, needSave = true, needRestore = false)
    }

    /**
     * @param needRestore 生效的前提是 [needSave]为true
     */
    private fun drawTest(canvas: Canvas, needSave:Boolean = true, needRestore:Boolean = true) {
        // canvas.saveCount 打印的是当前正在使用的layer
        Log.d(TAG, "onDraw: canvas.saveCount=" + canvas.saveCount) // 1
        paint.color = Color.RED
        canvas.drawRect(rect, paint)

        if (needSave) {
            // 返回的是保存的还原点，但是调用这个方法后实际上在用的就是第二个还原点了
            val count = canvas.save()
            Log.d(TAG, "onDraw canvas.save()=$count") // 1
        }



        Log.d(TAG, "onDraw: canvas.saveCount=" + canvas.saveCount + " new layer start") // 1
        canvas.translate(100f, 100f)
        paint.color = Color.BLUE
        canvas.drawRect(rect, paint)

        // restore和save必须要搭配调用，不然会报错
        if (needRestore && needSave) {
            Log.d(TAG, "onDraw: canvas.saveCount=" + canvas.saveCount + " new layer end") // 2
            // 调用了restore方法之后，就把当前的画布就指向之前的那个了。还原点是是通过栈来管理的.注意区分saveLayer
            canvas.restore()
        }


        canvas.translate(150f, 150f)
        paint.color = Color.GREEN
        canvas.drawRect(rect, paint)
        Log.d(TAG, "onDraw: canvas.saveCount=" + canvas.saveCount) // 1
    }

}