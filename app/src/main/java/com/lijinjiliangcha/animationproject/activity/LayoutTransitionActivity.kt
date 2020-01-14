package com.lijinjiliangcha.animationproject.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import com.lijinjiliangcha.animationproject.R
import com.lijinjiliangcha.animationproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_layout_transition.*

class LayoutTransitionActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_layout_transition
    }

    override fun init(savedInstanceState: Bundle?) {
        btn_add.setOnClickListener {
            val tv = TextView(context)
            tv.text = "ADD"
            tv.setTextColor(Color.BLACK)
            bl.addView(tv)
        }
    }
}