package com.lijinjiliangcha.animationproject.view

import android.util.Log
import android.view.animation.Interpolator

class BounceInInterpolator : Interpolator {

    private val in_1 = 0.6f
    private val in_2 = 0.8f

//    private val in_2_value: Float

    private val in_2_space = 0.1f
    private val in_3_space = 0.05f

    constructor() {
//        in_2_value = in_1 + in_2
    }


    override fun getInterpolation(input: Float): Float {
//        Log.i("测试", "input = $input")
//        return 1 - (input - 1) * (input - 1)
        if (input < in_1)
            return input / in_1
        if (input < in_2)
            return (1 - in_2_space) + (in_2_space * fromIn2(input))
        return 1 - in_3_space - (in_2_space - in_3_space) * fromIn3(input)
    }

    // 1 -> 0
    private fun fromIn2(input: Float): Float {
        return (in_2 - input) / (in_2 - in_1)
    }

    // 0 -> 1
    private fun fromIn3(input: Float): Float {
        return (1 - input) / (1 - in_2)
    }
}