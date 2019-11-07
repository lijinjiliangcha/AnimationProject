package com.lijinjiliangcha.animationproject

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected val context: Context by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        init(savedInstanceState)
    }

    abstract fun getContentView(): Int
    abstract fun init(savedInstanceState: Bundle?)
}