package com.lijinjiliangcha.animationproject.activity

import android.content.Intent
import android.os.Bundle
import com.lijinjiliangcha.animationproject.R
import com.lijinjiliangcha.animationproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        btn_heartbeat.setOnClickListener {
            startActivity(Intent(context, HeartbeatActivity::class.java))
        }
        btn_layoutTransition.setOnClickListener {
            startActivity(Intent(context, LayoutTransitionActivity::class.java))
        }
    }

}
