package com.firelord.weathering.presentation.ui

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.firelord.weathering.data.Constants
import com.firelord.weathering.core.data.Preferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Weathering : Application() {

    override fun onCreate() {
        super.onCreate()
        applyAppTheme()
    }

    private fun applyAppTheme() {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val themeValue = sharedPreferences.getString("app_theme", "system")

        when (themeValue) {
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "system" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}