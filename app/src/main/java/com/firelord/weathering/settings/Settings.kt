package com.firelord.weathering.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firelord.weathering.databinding.ActivitySettingsBinding

class Settings : AppCompatActivity() {

    private lateinit var settingsActivity: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsActivity = ActivitySettingsBinding.inflate(layoutInflater)
        val view = settingsActivity.root
        setContentView(view)
    }
}