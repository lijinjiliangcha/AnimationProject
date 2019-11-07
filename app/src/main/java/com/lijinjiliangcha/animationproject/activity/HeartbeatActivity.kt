package com.lijinjiliangcha.animationproject.activity

import android.os.Bundle
import com.lijinjiliangcha.animationproject.R
import com.lijinjiliangcha.animationproject.widget.HeartbeatLineView
import kotlinx.android.synthetic.main.activity_heartbeat.*

//心跳
class HeartbeatActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_heartbeat
    }

    override fun init(savedInstanceState: Bundle?) {
        heartLineView.start()

        btn.setOnClickListener {
//            heartLineView.setDuration(10000)
            heartLineView.setDisplayRange(0.3f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        heartLineView.end()
    }
}