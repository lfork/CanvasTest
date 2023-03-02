package com.lfork.canvastest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap

class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "MyView"

    private val fadingEdgeHelper = FadingEdgeHelper(context!!)

    // 重写该方法，进行绘图
    @SuppressLint("UseCompatLoadingForDrawables", "SuspiciousIndentation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        fadingEdgeHelper.fadingEdgeLength = 400
        val bitmap = context.getDrawable(R.drawable.test2)?.toBitmap()!!
        fadingEdgeHelper.saveCanvasLayer(canvas, width, height)
        // canvas.drawBitmap(bitmap, 0f,0f,null)
        fadingEdgeHelper.drawHorizontal(canvas, width, height)
        // fadingEdgeHelper.restoreCanvas(canvas)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

}