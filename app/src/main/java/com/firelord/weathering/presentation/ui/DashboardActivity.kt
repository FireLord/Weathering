package com.firelord.weathering.presentation.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.firelord.weathering.R
import com.firelord.weathering.data.Constants
import com.firelord.weathering.databinding.ActivityDashboardBinding
import com.firelord.weathering.presentation.viewmodel.WeatherViewModel
import com.firelord.weathering.presentation.viewmodel.WeatherViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding
    @Inject
    lateinit var factory: WeatherViewModelFactory
    lateinit var viewModel: WeatherViewModel
    private var currentPosition: Int = Constants.POSITION_HOME

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