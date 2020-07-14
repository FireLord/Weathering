package com.firelord.weathering

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
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
                    } else {
                        var errorMsg: String? = null
                        errorMsg = when (code()) {
                            429 -> "Server error: reached limit"
                            404 -> "Location not found"
                            401 -> "Server error: Unauthorized"
                            else -> "Server error: unknown"
                        }
                        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}