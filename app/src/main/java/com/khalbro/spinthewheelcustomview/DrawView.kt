package com.khalbro.spinthewheelcustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val textStorage = TextStorage

    var fontPaint: Paint? = null
    var redPaint: Paint? = null
    var text: String = textStorage.textColorSectors()
    var fontSize = 100
    private var widths: FloatArray
    var width = 0f

    init {
        redPaint = Paint()
        redPaint!!.color = Color.RED
        fontPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        fontPaint!!.textSize = fontSize.toFloat()
        fontPaint!!.style = Paint.Style.STROKE
        width = fontPaint!!.measureText(text)
        widths = FloatArray(text.length)
        fontPaint!!.getTextWidths(text, widths)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawARGB(80, 102, 204, 255)
        canvas.translate(50f, 250f)
        text.let { canvas.drawText(it, 0f, 0f, fontPaint!!) }
        canvas.drawLine(0f, 0f, width, 0f, fontPaint!!)
        canvas.drawCircle(0f, 0f, 3f, redPaint!!)
        for (w in widths) {
            canvas.translate(w, 0f)
            canvas.drawCircle(0f, 0f, 3f, redPaint!!)
        }
    }
}