package com.trainee.appinventiv.sigmaekatra

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import android.provider.Settings

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var deviceId : String
        // lateinit var  receivedToken : String
    }
    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID).toString()

    }
}