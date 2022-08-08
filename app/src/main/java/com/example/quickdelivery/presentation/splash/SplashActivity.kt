package com.example.quickdelivery.presentation.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quickdelivery.R
import com.example.quickdelivery.changeStatusBarColor
import com.example.quickdelivery.presentation.login.LoginActivity
import com.example.quickdelivery.setScreenFullSize
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setScreenFullSize()
        changeStatusBarColor(Color.WHITE)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(SPLASH_SCREEN_DURATION_TIME))
            withContext(Dispatchers.Main) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        //Bu değer splash screen ekranının kaç saniye kalacagı
        private val SPLASH_SCREEN_DURATION_TIME = 1L
    }
}