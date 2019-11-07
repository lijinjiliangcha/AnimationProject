package com.lijinjiliangcha.heartbeat

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class HeartbeatView : ConstraintLayout {

    val iv_icon: ImageView by lazy { ImageView(context) }

    private val iconAnima: Animation by lazy { initIconAnima() }

    //动画播放开关
    private var animaFlag = true

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeartbeatView)
        val iconId: Int = typeArray.getResourceId(R.styleable.HeartbeatView_icon, R.mipmap.heat)
        val iconSize: Int = typeArray.getDimensionPixelSize(R.styleable.HeartbeatView_iconSize, 100)
        typeArray.recycle()

        val ivLayoutParams = LayoutParams(iconSize, iconSize)
        ivLayoutParams.leftToLeft = LayoutParams.PARENT_ID
        ivLayoutParams.rightToRight = LayoutParams.PARENT_ID
        ivLayoutParams.topToTop = LayoutParams.PARENT_ID
        ivLayoutParams.bottomToBottom = LayoutParams.PARENT_ID
        iv_icon.layoutParams = ivLayoutParams
        iv_icon.setImageResource(iconId)
        iv_icon.scaleType = ImageView.ScaleType.FIT_XY
        addView(iv_icon)

    }

    private val iconListener = object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
            Log.i("测试", "icon - Repeat")
        }

        override fun onAnimationEnd(animation: Animation?) {
            Log.i("测试", "icon - 结束")
            if (animaFlag)
                iv_icon.postDelayed({ startAnimation() }, 1000)
        }

        override fun onAnimationStart(animation: Animation?) {
            Log.i("测试", "icon - 开始")
        }

    }

    private fun initIconAnima(): Animation {
        val anima = AnimationUtils.loadAnimation(context, R.anim.anim_heart_icon)
        anima.setAnimationListener(iconListener)
        return anima
    }

    fun startAnimation() {
        animaFlag = true
        iv_icon.startAnimation(iconAnima)
    }

    fun stopAnimation() {
        animaFlag = false
        iconAnima.cancel()
    }

}