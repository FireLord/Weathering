package com.firelord.weathering.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.PreferenceManager
import com.firelord.weathering.databinding.ActivityMainBinding
import com.firelord.weathering.presentation.ui.intro.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)

        // Start MainActivity for location
        val intent = Intent(this, ComposeActivity::class.java)
        startActivity(intent)
        finish()

//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
//        val activityOpened = sharedPreferences.getBoolean("activityOpened", false)
//        if (activityOpened){
//            // Start DashBoard as location is there
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
//            finish()
//        } else {
//            // Start MainActivity for location
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }
}