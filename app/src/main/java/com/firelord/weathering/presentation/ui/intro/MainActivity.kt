package com.firelord.weathering.presentation.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.R
import com.firelord.weathering.core.utils.Utilities.checkNetwork
import com.firelord.weathering.databinding.ActivityMainBinding
import com.firelord.weathering.presentation.ui.DashboardActivity
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Material editText
        mainBinding.textInputLayoutOutlined.setEndIconOnClickListener {
            // Set 'city' from user input
            val city = mainBinding.textInputEditTextOutlined.text.toString()
            if (city.isNotEmpty()) {
                // if 'city' is not empty start with network check
                if (checkNetwork(this)) {
                    // if user has network start api service
                    lifecycleScope.launch {
                        //getApi(city)
                    }
                } else {
                    // if user has no network report them with toast
                    Toast.makeText(this, getString(R.string.str_no_net), Toast.LENGTH_SHORT).show()
                }
            } else {
                // if 'city' is empty report user with toast
                Toast.makeText(this, getString(R.string.str_no_loc), Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this,DashboardActivity::class.java))
        }
    }

    // create instance from api interface
    // val apiService = WeatherRepository()

    // Use deg provided by api to set wind direction
    fun getWindDirection(deg: Int): String {
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

    // Set month name from calender util
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

    // Set date using various calendar output
    val date: String? = "${monthName(
        Calendar.getInstance()
            .get(Calendar.MONTH)
    )} ${Calendar.getInstance()
        .get(Calendar.DATE)}, ${Calendar.getInstance()
        .get(Calendar.YEAR)}"

    /*@SuppressLint("SetTextI18n")
    // Start api call using kotlin coroutines
    suspend fun getApi(city: String) {
        // When api call starts set outline circle check
        mainBinding.textInputLayoutOutlined.endIconDrawable =
            ContextCompat.getDrawable(this, R.drawable.ic_check_circle_outline)
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
                                    val home =
                                        Intent(this@MainActivity, DashboardActivity::class.java)
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
                                            R.color.colorSnowFont
                                        )
                                    )
                                    startActivity(home)
                                } else if (weatherNumber.id in 500..531 || weatherNumber.id in 300..321 || weatherNumber.id in 200..232 || weatherNumber.id == 701 || weatherNumber.id in 803..804) {
                                    // Rain
                                    val home =
                                        Intent(this@MainActivity, DashboardActivity::class.java)
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
                                            R.color.colorRainFont
                                        )
                                    )
                                    startActivity(home)
                                } else {
                                    // Sunny & others
                                    val home =
                                        Intent(this@MainActivity, DashboardActivity::class.java)
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
                                            R.color.colorSunnyFont
                                        )
                                    )
                                    startActivity(home)
                                }
                                // After successful fetching data set filled circle icon
                                mainBinding.textInputLayoutOutlined.endIconDrawable =
                                    ContextCompat.getDrawable(
                                        this@MainActivity,
                                        R.drawable.ic_check_circle
                                    )
                            }
                        }
                    } else {
                        val errorMsg: String?
                        errorMsg = when (code()) {
                            *//*
                            These responses are from OWA website
                            https://openweathermap.org/faq at last
                            *//*
                            429 -> getString(R.string.str_limit_error)
                            404 -> getString(R.string.str_location_error)
                            else -> getString(R.string.str_server_error)
                        }
                        withContext(Dispatchers.Main) {
                            *//*
                            On error for above reasons, display the message
                            to the user using material editText error ui
                            *//*
                            mainBinding.textInputLayoutOutlined.error = errorMsg
                            *//*
                            When user starts to fix his/her error bcz of location
                            not found change the error icon to arrow again and
                            remove material error ui
                            *//*
                            mainBinding.textInputEditTextOutlined.addTextChangedListener(
                                object :
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
                                        // Here material error ui is removed
                                        mainBinding.textInputLayoutOutlined.error = null
                                        // Here icon is changed to arrow
                                        mainBinding.textInputLayoutOutlined.endIconDrawable =
                                            ContextCompat.getDrawable(
                                                this@MainActivity,
                                                R.drawable.ic_arrow
                                            )
                                    }
                                })
                        }
                    }
                }
        }
    }*/
}
