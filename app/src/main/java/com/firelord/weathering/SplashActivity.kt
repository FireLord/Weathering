package com.firelord.weathering

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivityMainBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var splashActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = splashActivity.root
        setContentView(view)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}