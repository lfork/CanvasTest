package com.lfork.canvastest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CanvasSaveLayerTestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "CanvasSaveLayerTestView"

    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
    }
    private val rect = RectF(0f, 0f, 400f, 400f)
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw1:  saveCount=${canvas.saveCount}")
        val saveCount2 = canvas.saveLayer(rect, paint)
        Log.d(TAG, "onDraw2:  saveCount=${canvas.saveCount},  saveCount2=$saveCount2")
        canvas.drawColor(Color.RED)
        canvas.drawBitmap(bitmap, 200f, 200f, paint)
        canvas.restore()
        canvas.drawBitmap(bitmap, 400f, 400f, paint)
        Log.d(TAG, "onDraw3:  saveCount=${canvas.saveCount}")
    }
}