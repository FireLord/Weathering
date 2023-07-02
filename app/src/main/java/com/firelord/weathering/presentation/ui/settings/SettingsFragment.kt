package com.firelord.weathering.presentation.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.firelord.weathering.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())

        val themePreference: ListPreference? = findPreference("darkMode")
        themePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                val themeValue = newValue as String
                sharedPreferences.edit().putString("app_theme", themeValue).apply()
                applyAppTheme(themeValue)
                true
            }

        val unitPreference: ListPreference? = findPreference("unitMode")
        unitPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener {_,newValue ->
                val unitValue = newValue as String
                sharedPreferences.edit().putString("unitValue", unitValue).apply()
                true
            }

        val autoLocationSwitchPreference = findPreference<SwitchPreferenceCompat>("autoLocation")
        val manualLocationEditTextPreference = findPreference<EditTextPreference>("manualLocation")

        // Set the preference change listener on the SwitchPreferenceCompat
        autoLocationSwitchPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                val isEnabled = newValue as Boolean

                // Enable or disable the EditTextPreference based on the switch state
                manualLocationEditTextPreference?.isEnabled = !isEnabled
                true // Return true to allow the preference change
            }

        manualLocationEditTextPreference?.setOnPreferenceChangeListener { preference, newValue ->
            val newValue = newValue as String
            sharedPreferences.edit().putString("location", newValue).apply()
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