package com.lijinjiliangcha.animationproject.widget

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.lijinjiliangcha.animationproject.R

//心跳线
class HeartbeatLineView : View {

    private val defaultPointList: ArrayList<Point> by lazy { initDefaultPoint() }

    private val defaultColor = 0xFFD81B60.toInt()
    private var lineColor: Int
    private var lineWidth: Float

    private val linePaint: Paint

    //显示范围
    private var displayRange: Float = 0.5f
    //范围大小
    private var rangeSize: Float = 0f
    //显示大小
    private var displaySize: Float = 0f
    //初始偏移量
    private var startOffset: Float = 0f
    //线的轨迹
    private var path: Path? = null
    //点数据
    private var pointList: ArrayList<Point> = ArrayList()
    //动画
    private val animation: ValueAnimator by lazy { initAnimation() }
    //动画进度
    private var animProgress = 0f
    //动画开关 true - 开
    private var animFlag = true

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeartbeatLineView)
        lineColor = typeArray.getColor(R.styleable.HeartbeatLineView_lineColor, defaultColor)
        lineWidth = typeArray.getDimension(R.styleable.HeartbeatLineView_lineWidth, 4f)
        typeArray.recycle()

        linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.color = lineColor
        linePaint.strokeWidth = lineWidth
        linePaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //计算坐标变量
        val halfY = h.toFloat() / 2
        rangeSize = w.toFloat() * displayRange
        startOffset = -rangeSize
        //显示大小需要加上滑进部分，否则将失去滑出动画
        displaySize = w + rangeSize
        Log.i("测试", "rangeSize = $rangeSize，startOffset = $startOffset，displaySize = $displaySize")
        //没有数据时获取默认数据
        if (pointList.size == 0)
            pointList.addAll(defaultPointList)
        path = Path()
        path!!.moveTo(0f, halfY)
        pointList.forEach {
            path!!.lineTo(w * it.xLocat, when (it.pointPlace.value) {
                PointPlace.TOP.value -> 0f
                PointPlace.BOTTOM.value -> h.toFloat()
                else -> halfY
            })
        }
        path!!.lineTo(w.toFloat(), halfY)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (path != null) {
            //先裁剪后绘制
            val l = startOffset + displaySize * animProgress
            val r = l + rangeSize
            Log.i("测试", "l = $l，r = $r")
            canvas?.clipRect(l, 0f, r, height.toFloat())
            canvas?.drawPath(path!!, linePaint)
        }
    }

    //动画数据更新监听
    private val updateListener = object : ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            animProgress = animation.animatedValue as Float
            Log.i("测试", "animProgress = $animProgress")
            invalidate()
        }
    }

    //动画播放监听
    private val animaListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            Log.i("测试", "动画重播")
        }

        override fun onAnimationEnd(animation: Animator?) {
            Log.i("测试", "动画结束")
            if (animFlag)
                animation?.start()
        }

        override fun onAnimationCancel(animation: Animator?) {
            Log.i("测试", "动画取消")
        }

        override fun onAnimationStart(animation: Animator?) {
            Log.i("测试", "动画开始")
        }
    }

    fun start() {
        animFlag = true
        animation.start()
    }

    fun end() {
        animFlag = false
        animation.cancel()
    }

    private fun initAnimation(): ValueAnimator {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener(updateListener)
        animator.setDuration(2000)
        animator.interpolator = LinearInterpolator()
//        animator.repeatMode = ValueAnimator.RESTART
//        animator.repeatCount = Int.MAX_VALUE
        animator.addListener(animaListener)
        return animator
    }


    private fun initDefaultPoint(): ArrayList<Point> {
        val list = ArrayList<Point>()
        //左
        list.add(Point(0.08f, PointPlace.CENTER))
        list.add(Point(0.16f, PointPlace.TOP))
        list.add(Point(0.24f, PointPlace.BOTTOM))
        list.add(Point(0.32f, PointPlace.CENTER))
        //右
        list.add(Point(0.68f, PointPlace.CENTER))
        list.add(Point(0.76f, PointPlace.TOP))
        list.add(Point(0.84f, PointPlace.BOTTOM))
        list.add(Point(0.92f, PointPlace.CENTER))
        return list
    }

    class Point {
        //以百分比来控制点的x坐标，取值范围0 - 1
        val xLocat: Float
        //点的位置
        val pointPlace: PointPlace

        constructor(xLocat: Float, pointPlace: PointPlace) {
            this.xLocat = xLocat
            this.pointPlace = pointPlace
        }
    }

    /**
     * 点的位置枚举类
     * top - 顶部
     * center - 正中
     * bottom - 底部
     */
    enum class PointPlace(val value: Int) {
        TOP(0),
        CENTER(1),
        BOTTOM(2)
    }
}