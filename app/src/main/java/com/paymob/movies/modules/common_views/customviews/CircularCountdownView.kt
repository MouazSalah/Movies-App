package com.paymob.movies.modules.common_views.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircularCountdownView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var progress = 0f // 0f to 100f
    private val maxProgress = 100f

    private val backgroundPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private val progressPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    var timeLeft: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    fun setProgress(newProgress: Float) {
        this.progress = newProgress
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width - paddingLeft - paddingRight
        val height = height - paddingTop - paddingBottom
        val size = Math.min(width, height).toFloat()
        val cx = paddingLeft + width / 2
        val cy = paddingTop + height / 2

        // Draw background circle
        canvas.drawCircle((cx).toFloat(), (cy).toFloat(), size / 2, backgroundPaint)

        // Draw progress circle
        val sweepAngle = 360 * progress / maxProgress
        canvas.drawArc(
            paddingLeft.toFloat(), paddingTop.toFloat(), paddingLeft + size, paddingTop + size,
            -90f, sweepAngle, false, progressPaint
        )

        // Draw countdown text in center
        canvas.drawText(timeLeft.toString(), cx.toFloat(), cy + (textPaint.textSize / 3), textPaint)
    }
}
