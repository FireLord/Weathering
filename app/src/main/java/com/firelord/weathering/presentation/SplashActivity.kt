package com.firelord.weathering.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivityMainBinding
import com.firelord.weathering.presentation.intro.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)

        // Start MainActivity for location
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}