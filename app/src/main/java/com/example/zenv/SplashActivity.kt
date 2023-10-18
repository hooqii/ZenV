package com.example.zenv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIMEOUT: Long = 2000 // Waktu tampilan splash screen dalam milidetik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val mainIntent = Intent(this@SplashActivity, WellcomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_TIMEOUT)
    }
}
