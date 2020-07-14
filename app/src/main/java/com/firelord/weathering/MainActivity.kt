package com.firelord.weathering

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.data.OpenWeatherServiceApi
import com.firelord.weathering.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivity.root
        setContentView(view)

        mainActivity.buReport.setOnClickListener {
            val city = mainActivity.etLocation.text.toString()
            if (city.isNotEmpty()) {
                lifecycleScope.launch {
                    getApi(city)
                }
            } else {
                Toast.makeText(this, "Please enter location", Toast.LENGTH_SHORT).show()
            }
        }
        supportActionBar?.elevation = 0F
    }

    val apiService = OpenWeatherServiceApi()

    @SuppressLint("SetTextI18n")
    suspend fun getApi(city: String) {
        withContext(IO) {
            apiService.getCurrentWeather(city, "metric").awaitResponse()
                .run {
                    if (isSuccessful) {
                        body()?.let {
                            withContext(Dispatchers.Main) {
                                mainActivity.tvTemp.text = "${it.main.temp.toInt()}°C"
                                mainActivity.tvRain.text = "${it.clouds.all}%"
                                mainActivity.tvHumidity.text = "${it.main.humidity}%"
                                mainActivity.tvWind.text = "${it.wind.deg}" //TODO take deg change to sw/e/n
                                mainActivity.tvDay.text = Calendar.getInstance().getDisplayName(
                                    Calendar.DAY_OF_WEEK,
                                    Calendar.LONG,
                                    Locale.getDefault()
                                )
                                mainActivity.tvDate.text = "${Calendar.getInstance()
                                    .get(Calendar.DATE)}/${Calendar.getInstance()
                                    .get(Calendar.MONTH)}/${Calendar.getInstance()
                                    .get(Calendar.YEAR)}"
                            }
                        }
                    }
                }
        }
    }
}