package com.firelord.weathering

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.data.OpenWeatherServiceApi
import com.firelord.weathering.databinding.ActivityMainBinding
import com.firelord.weathering.home.Home
import com.firelord.weathering.home.WeatherModel
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

        mainActivity.textInputLayoutOutlined.setEndIconOnClickListener {
            val city = mainActivity.textInputEditTextOutlined.text.toString()
            if (city.isNotEmpty()) {
                if (checkNetwork(this)) {
                    lifecycleScope.launch {
                        getApi(city)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.noNet), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.noLoc), Toast.LENGTH_SHORT).show()
            }
        }
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

    fun monthName(month: Int): String {
        return when (month) {
            0 -> "Jan"
            1 -> "Feb"
            2 -> "Mar"
            3 -> "Apr"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "Aug"
            8 -> "Sep"
            9 -> "Oct"
            10 -> "Nov"
            11 -> "Dec"
            else -> "error"
        }
    }

    val date: String? = "${monthName(
        Calendar.getInstance()
            .get(Calendar.MONTH)
    )} ${Calendar.getInstance()
        .get(Calendar.DATE)}, ${Calendar.getInstance()
        .get(Calendar.YEAR)}"

    @SuppressLint("SetTextI18n")
    suspend fun getApi(city: String) {
        mainActivity.textInputLayoutOutlined.endIconDrawable =
            getDrawable(R.drawable.ic_check_circle_outline)
        withContext(IO) {
            apiService.getCurrentWeather(city, "metric").awaitResponse()
                .run {
                    if (isSuccessful) {
                        body()?.let {
                            withContext(Dispatchers.Main) {
                                val weatherNumber = it.weather[0]
                                // https://openweathermap.org/weather-conditions
                                if (weatherNumber.id in 600..622) {
                                    // Snow
                                    val home = Intent(this@MainActivity, Home::class.java)
                                    home.putExtra(
                                        "weatherModel", WeatherModel(
                                            "${it.main.temp.toInt()}°",
                                            "${it.clouds.all}%",
                                            "${it.main.humidity}%",
                                            "${it.wind.speed}ms",
                                            weatherNumber.main,
                                            date,
                                            "${city}, ${it.sys.country}",
                                            R.drawable.snow,
                                            R.drawable.ic_snow_rain,
                                            R.drawable.ic_snow_hum,
                                            R.drawable.ic_snow_wind,
                                            R.color.colorRainDark
                                        )
                                    )
                                    startActivity(home)
                                } else if (weatherNumber.id in 500..531 || weatherNumber.id in 300..321 || weatherNumber.id in 200..232 || weatherNumber.id == 701 || weatherNumber.id in 803..804) {
                                    // Rain
                                    val home = Intent(this@MainActivity, Home::class.java)
                                    home.putExtra(
                                        "weatherModel", WeatherModel(
                                            "${it.main.temp.toInt()}°",
                                            "${it.clouds.all}%",
                                            "${it.main.humidity}%",
                                            "${it.wind.speed}ms",
                                            weatherNumber.main,
                                            date,
                                            "${city}, ${it.sys.country}",
                                            R.drawable.rain,
                                            R.drawable.ic_rain_rain,
                                            R.drawable.ic_rain_hum,
                                            R.drawable.ic_rain_wind,
                                            R.color.colorRainDark
                                        )
                                    )
                                    startActivity(home)
                                } else {
                                    // Sunny & others
                                    val home = Intent(this@MainActivity, Home::class.java)
                                    home.putExtra(
                                        "weatherModel", WeatherModel(
                                            "${it.main.temp.toInt()}°",
                                            "${it.clouds.all}%",
                                            "${it.main.humidity}%",
                                            "${it.wind.speed}ms",
                                            weatherNumber.main,
                                            date,
                                            "${city}, ${it.sys.country}",
                                            R.drawable.sunny,
                                            R.drawable.ic_sunny_rain,
                                            R.drawable.ic_sunny_hum,
                                            R.drawable.ic_sunny_wind,
                                            R.color.sunnyPink
                                        )
                                    )
                                    startActivity(home)
                                }
                                mainActivity.textInputLayoutOutlined.endIconDrawable =
                                    getDrawable(R.drawable.ic_check_circle)
                            }
                        }
                    } else {
                        var errorMsg: String? = null
                        errorMsg = when (code()) {
                            429 -> getString(R.string.limitError)
                            404 -> getString(R.string.locationError)
                            else -> getString(R.string.serverError)
                        }
                        withContext(Dispatchers.Main) {
                            mainActivity.textInputLayoutOutlined.error = errorMsg
                            mainActivity.textInputEditTextOutlined.addTextChangedListener(object :
                                TextWatcher {
                                override fun onTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {
                                }

                                override fun beforeTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    count: Int,
                                    aft: Int
                                ) {
                                }

                                override fun afterTextChanged(s: Editable) {
                                    mainActivity.textInputLayoutOutlined.error = null
                                    mainActivity.textInputLayoutOutlined.endIconDrawable =
                                        getDrawable(R.drawable.ic_arrow)
                                }
                            })
                        }
                    }
                }
        }
    }
}
