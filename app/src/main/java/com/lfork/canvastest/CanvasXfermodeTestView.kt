package com.lfork.canvastest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CanvasXfermodeTestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "CanvasXfermodeTestView"
    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
        isDither = true
        isFilterBitmap = true
    }
    private val rect = RectF(0f, 0f, 400f, 400f)
    private val rectArea = RectF(0f, 0f, 600f, 600f)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw1:  saveCount=${canvas.saveCount}")
        xferModeTest(canvas)
    }


    private fun xferModeTest(canvas: Canvas){
        // canvas.drawColor(Color.GREEN)
        val saveCount = canvas.saveLayer(rectArea, paint)

        Log.d(TAG, "onDraw2:  saveCount=${canvas.saveCount}")
        canvas.drawRect(rect, paint)
        paint.xfermode = xfermode
        paint.color = Color.BLUE
        canvas.drawBitmap(makeSrc(), 0f, 0f, paint)
        paint.xfermode = null
        Log.d(TAG, "onDraw3:  saveCount=${canvas.saveCount}")
        canvas.restoreToCount(saveCount)
        Log.d(TAG, "onDraw4:  saveCount=${canvas.saveCount}")
    }

    private fun saveCountTest(canvas: Canvas){

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawRect(rect, paint)


        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        canvas.save()
        canvas.translate(100f, 100f)
        paint.color = Color.BLUE
        canvas.drawRect(rect, paint)

        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        canvas.save()
        canvas.translate(100f, 100f)
        paint.color = Color.BLACK
        canvas.drawRect(rect, paint)

        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        canvas.save()
        canvas.translate(100f, 100f)
        paint.color = Color.YELLOW
        canvas.drawRect(rect, paint)

        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        canvas.restoreToCount(1)
        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        paint.color = Color.GREEN
        canvas.drawRect(rect, paint)


        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        canvas.restoreToCount(2)
        Log.d(TAG, "saveCountTest: canvas.saveCount=${canvas.saveCount}")
        paint.color = Color.CYAN
        canvas.drawRect(rect, paint)


    }


    private fun makeSrc(): Bitmap {
        val bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        canvas.drawCircle(400f, 400f, 200f, paint)
        return bitmap
    }
}