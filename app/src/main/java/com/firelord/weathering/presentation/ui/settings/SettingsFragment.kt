package com.firelord.weathering.presentation.ui.settings

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.launch
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val REQUEST_CODE = 100
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

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
                if (isEnabled){
                    getLastLocation()
                    //sharedPreferences.edit().putString("city", cityName).apply()
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

        manualLocationEditTextPreference?.setOnPreferenceChangeListener { preference, newValue ->
            val newValue = newValue as String
            sharedPreferences.edit().putString("city", newValue).apply()
            true
        }

        manualLocationEditTextPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                sharedPreferences.getString("city","not set")
            }
    }

    private fun getLastLocation(){
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_CODE
            )
        }
        else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                    val addresses: List<Address>? = geocoder.getFromLocation(
                        location!!.latitude, location.longitude, 1
                    )
                    val cityName = addresses?.get(0)!!.getLocality()
                    val sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(requireContext())
                    sharedPreferences.edit().putString("city", cityName).apply()

                }
                .addOnFailureListener {
                    Log.e("fusedLocation",it.message.toString())
                }
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