package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivityRainBinding

class Rain : AppCompatActivity() {

    private lateinit var rainActivity: ActivityRainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rainActivity = ActivityRainBinding.inflate(layoutInflater)
        val view = rainActivity.root
        setContentView(view)

        setSupportActionBar(rainActivity.toolbar)

        val weatherInfo = intent.extras!!.getParcelable<WeatherModel>("weatherModel")
        rainActivity.tvTemp.text = weatherInfo!!.tvTemp
        rainActivity.tvRain.text = weatherInfo.tvRain
        rainActivity.tvHumidity.text = weatherInfo.tvHumidity
        rainActivity.tvWind.text = weatherInfo.tvWind
        rainActivity.tvDay.text = weatherInfo.tvDay
        rainActivity.tvDate.text = weatherInfo.tvDate
        rainActivity.tvLocation.text = weatherInfo.tvLocation
    }
}