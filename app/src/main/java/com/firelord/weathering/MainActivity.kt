package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.firelord.weathering.data.OpenWeatherServiceApi
import com.firelord.weathering.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivity.root
        setContentView(view)

        mainActivity.button.setOnClickListener {
            lifecycleScope.launch {
                getApi()
            }
        }
    }

    val apiService = OpenWeatherServiceApi()
    suspend fun getApi() {
        withContext(IO) {
            apiService.getCurrentWeather("Noida", "metric").awaitResponse()
                .run {
                    if (isSuccessful) {
                        body()?.let {
                            withContext(Dispatchers.Main) {
                                mainActivity.tvTemp.text = it.main.temp.toString()
                            }
                        }
                    }
                }
        }
    }
}