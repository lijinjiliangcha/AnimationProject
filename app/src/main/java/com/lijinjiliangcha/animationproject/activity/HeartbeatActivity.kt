package com.lijinjiliangcha.animationproject.activity

import android.os.Bundle
import com.lijinjiliangcha.animationproject.R
import com.lijinjiliangcha.animationproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_heartbeat.*

//心跳
class HeartbeatActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_heartbeat
    }

    override fun init(savedInstanceState: Bundle?) {
        heartLineView.start()
        heartView.startAnimation()

        btn.setOnClickListener {
            //heartLineView.setDuration(10000)
//            heartLineView.setDisplayRange(0.3f)
            heartView.startAnimation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        heartLineView.end()
        heartView.stopAnimation()
    }
}