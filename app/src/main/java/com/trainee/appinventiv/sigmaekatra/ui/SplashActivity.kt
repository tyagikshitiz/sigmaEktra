package com.trainee.appinventiv.sigmaekatra.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.trainee.appinventiv.sigmaekatra.MainActivity
import com.trainee.appinventiv.sigmaekatra.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler(Looper.myLooper()!!).postDelayed({
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}