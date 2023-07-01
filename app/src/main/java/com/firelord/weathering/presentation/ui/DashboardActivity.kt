package com.firelord.weathering.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.firelord.weathering.R
import com.firelord.weathering.data.Constants
import com.firelord.weathering.core.data.Preferences
import com.firelord.weathering.databinding.ActivityDashboardBinding
import com.firelord.weathering.presentation.ui.home.HomeFragment
import com.firelord.weathering.presentation.ui.home.WeatherModel
import com.firelord.weathering.presentation.ui.info.InfoFragment
import com.firelord.weathering.presentation.ui.search.SearchFragment
import com.firelord.weathering.presentation.ui.settings.BottomSheetDarkMode
import com.firelord.weathering.presentation.ui.settings.SettingsFragment
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        dashboardBinding.bnvWeather.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this,factory)[WeatherViewModel::class.java]
    }
}