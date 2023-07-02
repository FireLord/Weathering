package com.firelord.weathering.presentation.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.firelord.weathering.R
import com.firelord.weathering.presentation.ui.DashboardActivity
import com.firelord.weathering.presentation.viewmodel.WeatherViewModel

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var viewModel: WeatherViewModel
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        viewModel = (activity as DashboardActivity).viewModel

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
        val unitPreference: ListPreference? = findPreference("unitMode")
        unitPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener {_,newValue ->
                val unitValue = newValue as String
                when (unitValue) {
                    "metric" -> {
                        viewModel.weatherUnit.value = "metric"
                    }
                    "imperial" -> {
                        viewModel.weatherUnit.value = "imperial"
                    }
                }
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