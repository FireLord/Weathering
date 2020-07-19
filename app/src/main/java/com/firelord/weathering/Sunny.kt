package com.firelord.weathering

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.data.OpenWeatherServiceApi
import com.firelord.weathering.databinding.ActivitySunnyBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.*

class Sunny : AppCompatActivity() {

    private lateinit var SunnyActivity: ActivitySunnyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SunnyActivity = ActivitySunnyBinding.inflate(layoutInflater)
        val view = SunnyActivity.root
        setContentView(view)

        SunnyActivity.buReport.setOnClickListener {
            val city = SunnyActivity.etLocation.text.toString()
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

    @SuppressLint("SetTextI18n")
    suspend fun getApi(city: String) {
        SunnyActivity.progressBar.visibility = View.VISIBLE
        withContext(IO) {
            apiService.getCurrentWeather(city, "metric").awaitResponse()
                .run {
                    if (isSuccessful) {
                        body()?.let {
                            withContext(Dispatchers.Main) {
                                SunnyActivity.tvTemp.text = "${it.main.temp.toInt()}°C"
                                SunnyActivity.tvRain.text = "${it.clouds.all}%"
                                SunnyActivity.tvHumidity.text = "${it.main.humidity}%"
                                SunnyActivity.tvWind.text = getWindDirction(it.wind.deg)
                                SunnyActivity.tvDay.text = Calendar.getInstance().getDisplayName(
                                    Calendar.DAY_OF_WEEK,
                                    Calendar.LONG,
                                    Locale.getDefault()
                                )
                                SunnyActivity.tvDate.text = "${Calendar.getInstance()
                                    .get(Calendar.DATE)}/${Calendar.getInstance()
                                    .get(Calendar.MONTH)}/${Calendar.getInstance()
                                    .get(Calendar.YEAR)}"
                                SunnyActivity.progressBar.visibility = View.GONE
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
                            SunnyActivity.progressBar.visibility = View.GONE
                            Toast.makeText(this@Sunny, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}