package com.firelord.weathering.presentation.ui

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.firelord.weathering.data.Constants
import com.firelord.weathering.core.data.Preferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
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