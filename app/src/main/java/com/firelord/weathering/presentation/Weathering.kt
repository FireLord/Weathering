package com.firelord.weathering.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.firelord.weathering.data.Constants
import com.firelord.weathering.core.data.Preferences

class Weathering : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            Preferences(this).fetchInt(
                Constants.KEY_THEME_MODE,
                AppCompatDelegate.MODE_NIGHT_NO
            )
        )
    }
}