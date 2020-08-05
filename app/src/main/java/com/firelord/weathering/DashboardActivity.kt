package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.firelord.weathering.databinding.ActivityDashboardBinding
import com.firelord.weathering.home.Home
import com.firelord.weathering.home.WeatherModel
import com.firelord.weathering.info.Info
import com.firelord.weathering.settings.Settings

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = dashboardBinding.root
        setContentView(view)

        // Create single instance of Home fragment
        val homeFrag = Home()

        // Get Data from MainActivity
        val weatherInfo = intent.extras!!.getParcelable<WeatherModel>("weatherModel")

        // Set data to fragment
        val data = Bundle() //create bundle instance
        data.putString("tvTemp", weatherInfo!!.tvTemp)
        data.putString("tvRain", weatherInfo.tvRain)
        data.putString("tvHumidity", weatherInfo.tvHumidity)
        data.putString("tvWindSpeed", weatherInfo.tvWindSpeed)
        data.putString("tvWeatherType", weatherInfo.tvWeatherType)
        data.putString("tvDate", weatherInfo.tvDate)
        data.putString("tvLocation", weatherInfo.tvLocation)
        data.putInt("clCard", weatherInfo.clCard)
        data.putInt("tvRainName", weatherInfo.tvRainName)
        data.putInt("tvHumidityName", weatherInfo.tvHumidityName)
        data.putInt("tvWindName", weatherInfo.tvWindName)
        data.putInt("tvWeatherTypeColor", weatherInfo.tvWeatherTypeColor)
        homeFrag.arguments = data //Set bundle data to fragment

        dashboardBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == dashboardBinding.bottomNavigation.selectedItemId) {
                return@setOnNavigationItemSelectedListener false
            } else {
                when (item.itemId) {
                    R.id.home_category -> switchFrags(homeFrag)
                    R.id.search_category -> switchFrags(Search())
                    R.id.settings_category -> switchFrags(Settings())
                    R.id.info_category -> switchFrags(Info())
                }
                return@setOnNavigationItemSelectedListener true
            }
        }
        dashboardBinding.bottomNavigation.selectedItemId = R.id.home_category
        switchFrags(homeFrag)
    }

    private fun switchFrags(fragment: Fragment) {
        supportFragmentManager.commitNow {
            replace(R.id.fragment_frame, fragment)
        }
    }
}