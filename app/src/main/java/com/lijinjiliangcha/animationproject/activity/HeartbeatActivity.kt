package com.lijinjiliangcha.animationproject.activity

import android.os.Bundle
import com.lijinjiliangcha.animationproject.R
import kotlinx.android.synthetic.main.activity_heartbeat.*

//心跳
class HeartbeatActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_heartbeat
    }

    override fun init(savedInstanceState: Bundle?) {
        heartView.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        heartView.end()
    }
}