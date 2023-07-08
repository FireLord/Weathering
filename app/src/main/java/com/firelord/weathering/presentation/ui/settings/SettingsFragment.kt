package com.firelord.weathering.presentation.ui.settings

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.firelord.weathering.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var locationClient: LocationClient

    private var locationPermissionRequest: ActivityResultLauncher<Array<String>>? = null
    private var autoLocationSwitchPreference: SwitchPreferenceCompat? = null
    private var manualLocationEditTextPreference: EditTextPreference? = null
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

        autoLocationSwitchPreference = findPreference("autoLocation")
        manualLocationEditTextPreference = findPreference("manualLocation")

        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                    locationClient.getLastLocation {
                        autoLocationSwitchPreference?.summaryOn = sharedPreferences.getString("city","not set")
                    }
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                    getPermission() // if precise location permission is needed, just re invoke permission request
                } else -> {
                Toast.makeText(requireContext(), "This app needs location permission, please grant it.", Toast.LENGTH_LONG).show()
            }
            }
        }

        // Set the preference change listener on the SwitchPreferenceCompat
        autoLocationSwitchPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                val isEnabled = newValue as Boolean
                if (isEnabled){
                    getPermission()
                    manualLocationEditTextPreference?.text = ""
                }
                sharedPreferences.edit().putBoolean("autoLocation", isEnabled).apply()

                // Enable or disable the EditTextPreference based on the switch state
                manualLocationEditTextPreference?.isEnabled = !isEnabled
                true // Return true to allow the preference change
            }
        autoLocationSwitchPreference?.summaryOff = ""
        autoLocationSwitchPreference?.summaryOn = sharedPreferences.getString("city","not set")

        // Enable or disable the EditTextPreference based on the savedSwitch state
        // required when app opens again
        val autoBool = sharedPreferences.getBoolean("autoLocation",false)
        manualLocationEditTextPreference?.isEnabled = !autoBool

        manualLocationEditTextPreference?.setOnPreferenceChangeListener { _, newValue ->
            sharedPreferences.edit().putString("city", newValue as String).apply()
            true
        }

        manualLocationEditTextPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { _ ->
                sharedPreferences.getString("city","not set")
            }
    }

    private fun getPermission(){
        locationPermissionRequest?.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
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