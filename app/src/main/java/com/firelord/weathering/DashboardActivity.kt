package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.firelord.weathering.core.data.Constants
import com.firelord.weathering.core.data.Preferences
import com.firelord.weathering.databinding.ActivityDashboardBinding
import com.firelord.weathering.home.HomeFragment
import com.firelord.weathering.home.WeatherModel
import com.firelord.weathering.info.InfoFragment
import com.firelord.weathering.search.SearchFragment
import com.firelord.weathering.settings.BottomSheetDarkMode
import com.firelord.weathering.settings.SettingsFragment

class DashboardActivity : BottomSheetDarkMode.BottomSheetListener, AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding

    private var currentPosition: Int = Constants.POSITION_HOME
    private val fragmentList: ArrayList<Fragment> =
        arrayListOf(HomeFragment(), SearchFragment(), SettingsFragment(), InfoFragment())
    private var preferences: Preferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = dashboardBinding.root
        setContentView(view)

        preferences = Preferences(this)

        // Set Listener
        dashboardBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == dashboardBinding.bottomNavigation.selectedItemId) {
                return@setOnNavigationItemSelectedListener false
            } else {
                when (item.itemId) {
                    R.id.home_category -> {
                        currentPosition = Constants.POSITION_HOME
                        switchFrags()
                    }
                    R.id.search_category -> {
                        currentPosition = Constants.POSITION_SEARCH
                        switchFrags()
                    }
                    R.id.settings_category -> {
                        currentPosition = Constants.POSITION_SETTINGS
                        switchFrags()
                    }
                    R.id.info_category -> {
                        currentPosition = Constants.POSITION_INFO
                        switchFrags()
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }
        }
        savedInstanceState?.let {
            currentPosition = it.getInt(Constants.KEY_LAST_TAB)
        }
        switchFrags()
    }

    private fun switchFrags() {
        val fragment = fragmentList[currentPosition]
        if (currentPosition == Constants.POSITION_HOME) { // in case its home frag

            // Get Data from MainActivity
            val weatherInfo = intent.extras?.getParcelable<WeatherModel>("weatherModel")

            // Set data to fragment
            val data = Bundle() //create bundle instance
            weatherInfo?.let {
                data.putString("tvTemp", weatherInfo.tvTemp)
                data.putString("tvRain", weatherInfo.tvRain)
                data.putString("tvHumidity", weatherInfo.tvHumidity)
                data.putString("tvWindSpeed", weatherInfo.tvWindSpeed)
                data.putString("tvWeatherType", weatherInfo.tvWeatherType)
                data.putString("tvDate", weatherInfo.tvDate)
                data.putString("tvLocation", weatherInfo.tvLocation)
                data.putInt("ivBgWeather", weatherInfo.ivBgWeather)
                data.putInt("tvRainName", weatherInfo.tvRainName)
                data.putInt("tvHumidityName", weatherInfo.tvHumidityName)
                data.putInt("tvWindName", weatherInfo.tvWindName)
                data.putInt("tvWeatherTypeColor", weatherInfo.tvWeatherTypeColor)
            }
            fragment.arguments = data //Set bundle data to fragment
        }

        supportFragmentManager.commitNow {
            replace(R.id.fragment_frame, fragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constants.KEY_LAST_TAB, currentPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onButtonClicked(mode: Int) {
        preferences?.storeInt(Constants.KEY_THEME_MODE, mode)
        setDefaultNightMode(mode)
    }
}