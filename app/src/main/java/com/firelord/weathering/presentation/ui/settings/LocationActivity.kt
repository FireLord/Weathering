package com.firelord.weathering.presentation.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var locationBinding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationBinding = ActivityLocationBinding.inflate(layoutInflater)
        val view = locationBinding.root
        setContentView(view)

        locationBinding.fabSettingsLocation.setOnClickListener {
            finish()
        }
    }
}