package com.example.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val it = Intent(this,LoginActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(it)
            finish()
        },1500)

    }
}