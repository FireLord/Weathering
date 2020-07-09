package com.firelord.weathering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firelord.weathering.data.OpenWeatherServiceApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = OpenWeatherServiceApi()
        GlobalScope.launch(Dispatchers.IO) {
            val currentWeatherResponse = apiService.getCurrentWeather("Noida").await()
            tvTemp.text=currentWeatherResponse.main.toString()
        }
    }
}