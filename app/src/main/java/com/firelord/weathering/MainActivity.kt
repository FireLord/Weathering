package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.firelord.weathering.data.OpenWeatherServiceApi
import com.firelord.weathering.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val apiService = OpenWeatherServiceApi()
        GlobalScope.launch(Dispatchers.IO) {
            apiService.getCurrentWeather("Noida").awaitResponse()
                .run {
                    if(isSuccessful) {
                        body()?.let {
                            runOnUiThread {
                                mBinding.tvTemp.text = it.main.temp.toString()
                            }
                        }
                    }
                }
        }
    }
}