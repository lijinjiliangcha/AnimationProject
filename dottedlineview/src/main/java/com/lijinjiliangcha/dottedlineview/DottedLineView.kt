package com.lijinjiliangcha.dottedlineview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class DottedLineView : View {

    private val paint: Paint

    private var linePlace: Int
    private var path: Path? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.DottedLineView)
        val dottedLineWidth = typeArray.getDimension(R.styleable.DottedLineView_dottedLineWidth, 8f)
        val dottedLineSpace = typeArray.getDimension(R.styleable.DottedLineView_dottedLineSpace, 8f)
        val lineWidth = typeArray.getDimension(R.styleable.DottedLineView_lineWidth, 4f)
        val lineColor = typeArray.getColor(R.styleable.DottedLineView_lineColor, Color.RED)
        linePlace = typeArray.getInt(R.styleable.DottedLineView_linePlace, LinePlace.LT_RB.value)
        typeArray.recycle()

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.color = lineColor
        paint.strokeWidth = lineWidth
        paint.setPathEffect(DashPathEffect(floatArrayOf(dottedLineWidth, dottedLineSpace), 0f))

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path = Path()
        when (linePlace) {
            LinePlace.LB_RT.value -> {
                path!!.moveTo(0f, h.toFloat())
                path!!.lineTo(w.toFloat(), 0f)
            }
            else -> {
                path!!.moveTo(0f, 0f)
                path!!.lineTo(w.toFloat(), h.toFloat())
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (path != null)
            canvas?.drawPath(path!!, paint)
    }

    fun setLinePlace(place: LinePlace) {
        linePlace = place.value
        onSizeChanged(width, height, width, height)
        invalidate()
    }

    enum class LinePlace(val value: Int) {
        LT_RB(0),
        LB_RT(1)
    }

}