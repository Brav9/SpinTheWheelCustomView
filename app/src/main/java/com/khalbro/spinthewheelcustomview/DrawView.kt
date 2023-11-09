package com.khalbro.spinthewheelcustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Observer

class DrawView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val textStorage = TextStorage
    private var fontPaint: Paint? = Paint()
    private var widths: FloatArray
    var text: String = ""
    var fontSize = 100
    var width = 0f

    private val myObserver = Observer<String> { text = it }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        textStorage.currentTextColorLiveData.observeForever(myObserver)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        textStorage.currentTextColorLiveData.removeObserver(myObserver)
    }

    init {
        val redPaint = Paint()
        redPaint.color = Color.RED
        fontPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        fontPaint!!.textSize = fontSize.toFloat()
        fontPaint!!.style = Paint.Style.STROKE
        width = fontPaint!!.measureText(text)
        widths = FloatArray(text.length)
        fontPaint!!.getTextWidths(text, widths)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawARGB(80, 102, 204, 255)
        canvas.translate(550f, 90f)
        text.let { canvas.drawText(text, 0f, 0f, fontPaint!!) }
        invalidate()
    }
}