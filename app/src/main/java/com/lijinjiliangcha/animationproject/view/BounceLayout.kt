package com.lijinjiliangcha.animationproject.view

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.*
import android.widget.LinearLayout
import com.lijinjiliangcha.animationproject.R

class BounceLayout : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val anim = AnimationSet(false)
//        anim.addAnimation(AnimationUtils.loadAnimation(context, R.anim.view_in))
//        anim.addAnimation(AnimationUtils.loadAnimation(context, R.anim.view_in_bounce))

        val in_1 = TranslateAnimation(Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f)
        in_1.duration = 2000
        in_1.setInterpolator(BounceInInterpolator())

//        val in_2 = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.1f,
//                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f)
//        in_2.duration = 2000
//        in_2.startOffset = 3000
//        in_2.setInterpolator(LinearInterpolator())
//
//        val in_3 = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 0.05f,
//                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f)
//        in_3.duration = 2000
//        in_3.startOffset = 5000
//        in_3.setInterpolator(LinearInterpolator())

        anim.addAnimation(in_1)
//        anim.addAnimation(in_2)
//        anim.addAnimation(in_3)
        anim.fillAfter = true
        val lac = LayoutAnimationController(anim)

        layoutAnimation = lac

    }

//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//
//        Log.i("测试", "onSizeChanged,w = $w，h = $h")
//
//        val lt = LayoutTransition()
//        //时间
//        lt.setStagger(LayoutTransition.APPEARING, 3000)
//        lt.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 3000)
//        lt.setStagger(LayoutTransition.APPEARING, 3000)
//        lt.setStagger(LayoutTransition.DISAPPEARING, 3000)
//
//        val t1 = PropertyValuesHolder.ofFloat("translationX", w.toFloat(), 0f)
//        val v1 = ObjectAnimator.ofPropertyValuesHolder(t1)
//        lt.setAnimator(LayoutTransition.APPEARING, v1)
//
//        val t2 = PropertyValuesHolder.ofFloat("translationX", w.toFloat(), 0f)
//        val v2 = ObjectAnimator.ofPropertyValuesHolder(t2)
//        lt.setAnimator(LayoutTransition.DISAPPEARING, v2)
//
//        val t3 = PropertyValuesHolder.ofFloat("translationX", w.toFloat(), 0f)
//        val v3 = ObjectAnimator.ofPropertyValuesHolder(t3)
//        lt.setAnimator(LayoutTransition.CHANGE_APPEARING, v3)
//
//        val t4 = PropertyValuesHolder.ofFloat("translationX", w.toFloat(), 0f)
//        val v4 = ObjectAnimator.ofPropertyValuesHolder(t4)
//        lt.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, v4)
//
//        val t5 = PropertyValuesHolder.ofFloat("translationX", w.toFloat(), 0f)
//        val v5 = ObjectAnimator.ofPropertyValuesHolder(t5)
//        lt.setAnimator(LayoutTransition.CHANGING, v5)
//
//        layoutTransition = lt
//
//    }

}