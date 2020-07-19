package com.firelord.weathering

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.View
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

        mainActivity.button.setOnClickListener {
            val city = mainActivity.etLocation.text.toString()
            if (city.isNotEmpty()) {
                if (checkNetwork(this)) {
                    lifecycleScope.launch {
                        getApi(city)
                    }
                } else {
                    Toast.makeText(this, "Please turn on net", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter location", Toast.LENGTH_SHORT).show()
            }
        }
        supportActionBar?.elevation = 0F
    }

    val apiService = OpenWeatherServiceApi()

    fun checkNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        val connected = capabilities?.hasCapability(NET_CAPABILITY_INTERNET) == true
        return connected
    }

    fun getWindDirction(deg: Int): String {
        return when {
            deg > 337.5 -> "North"
            deg > 292.5 -> "North West"
            deg > 247.5 -> "West"
            deg > 202.5 -> "South West"
            deg > 157.5 -> "South"
            deg > 122.5 -> "South East"
            deg > 67.5 -> "East"
            deg > 22.5 -> "North East"
            else -> "North"
        }
    }

    val day: String? = Calendar.getInstance().getDisplayName(
        Calendar.DAY_OF_WEEK,
        Calendar.LONG,
        Locale.getDefault()
    )

    val date: String? = "${Calendar.getInstance()
        .get(Calendar.DATE)}/${Calendar.getInstance()
        .get(Calendar.MONTH)}/${Calendar.getInstance()
        .get(Calendar.YEAR)}"

    @SuppressLint("SetTextI18n")
    suspend fun getApi(city: String) {
        mainActivity.progressBar.visibility = View.VISIBLE
        withContext(IO) {
            apiService.getCurrentWeather(city, "metric").awaitResponse()
                .run {
                    if (isSuccessful) {
                        body()?.let {
                            withContext(Dispatchers.Main) {
                                val weatherNumber = it.weather[0]
                                // https://openweathermap.org/weather-conditions
                                if (weatherNumber.id in 600..622) {
                                    val snow = Intent(this@MainActivity, Snow::class.java)
                                    snow.putExtra(
                                        "weatherModel", WeatherModel(
                                            "${it.main.temp.toInt()}°C",
                                            "${it.clouds.all}%",
                                            "${it.main.humidity}%",
                                            getWindDirction(it.wind.deg),
                                            day,
                                            date,
                                            city
                                        )
                                    )
                                    startActivity(snow)
                                } else if (weatherNumber.id in 500..531 || weatherNumber.id in 300..321 || weatherNumber.id in 200..232 || weatherNumber.id == 701 || weatherNumber.id in 803..804 ) {
                                    val rain = Intent(this@MainActivity, Rain::class.java)
                                    rain.putExtra(
                                        "weatherModel", WeatherModel(
                                            "${it.main.temp.toInt()}°C",
                                            "${it.clouds.all}%",
                                            "${it.main.humidity}%",
                                            getWindDirction(it.wind.deg),
                                            day,
                                            date,
                                            city
                                        )
                                    )
                                    startActivity(rain)
                                } else {
                                    val sunny = Intent(this@MainActivity, Sunny::class.java)
                                    sunny.putExtra(
                                        "weatherModel", WeatherModel(
                                            "${it.main.temp.toInt()}°C",
                                            "${it.clouds.all}%",
                                            "${it.main.humidity}%",
                                            getWindDirction(it.wind.deg),
                                            day,
                                            date,
                                            city
                                        )
                                    )
                                    startActivity(sunny)
                                }
                                mainActivity.progressBar.visibility = View.GONE
                            }
                        }
                    } else {
                        var errorMsg: String? = null
                        errorMsg = when (code()) {
                            429 -> "Server error: reached limit"
                            404 -> "Location not found"
                            401 -> "Server error: Unauthorized"
                            else -> "Server error: unknown"
                        }
                        withContext(Dispatchers.Main) {
                            mainActivity.progressBar.visibility = View.GONE
                            Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}
