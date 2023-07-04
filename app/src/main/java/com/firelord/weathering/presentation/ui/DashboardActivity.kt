package com.firelord.weathering.presentation.ui

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivityDashboardBinding
import com.firelord.weathering.presentation.viewmodel.WeatherViewModel
import com.firelord.weathering.presentation.viewmodel.WeatherViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding
    @Inject
    lateinit var factory: WeatherViewModelFactory
    lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)

        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        dashboardBinding.bnvWeather.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this,factory)[WeatherViewModel::class.java]

        val receivedMessage = sharedPreferences.getString("city", "")
        viewModel.location.value = receivedMessage
    }
}