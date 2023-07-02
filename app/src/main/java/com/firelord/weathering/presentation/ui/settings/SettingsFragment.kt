package com.firelord.weathering.presentation.ui.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.firelord.weathering.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themePreference: ListPreference? = findPreference("darkMode")
        themePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                val themeValue = newValue as String
                val sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(requireContext())
                sharedPreferences.edit().putString("app_theme", themeValue).apply()
                applyAppTheme(themeValue)
                true
            }
    }
    private fun applyAppTheme(themeValue: String) {
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
        activity?.recreate()
    }
}