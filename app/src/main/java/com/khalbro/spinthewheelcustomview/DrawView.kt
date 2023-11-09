package com.khalbro.spinthewheelcustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    var text: String = ""
    private val widths = FloatArray(text.length)
    private val fontSize = 100
    private val fontPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = fontSize.toFloat()
        style = Paint.Style.STROKE
        getTextWidths(text, widths)
    }

    fun updateText(text: String) {
        this.text = text
        invalidate()
    }

    fun deleteText() {
        text = ""
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawARGB(80, 102, 204, 255)
        canvas.translate(550f, 90f)
        text.let { canvas.drawText(text, 0f, 0f, fontPaint) }
        invalidate()
    }
}