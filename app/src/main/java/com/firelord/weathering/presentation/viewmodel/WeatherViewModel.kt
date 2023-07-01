package com.firelord.weathering.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.domain.usecase.GetWeatherInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val app:Application,
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase
):AndroidViewModel(app) {
    val weatherInfo : MutableLiveData<Resource<RemoteFetch>> = MutableLiveData()
    val location: MutableLiveData<String> = MutableLiveData()
    val successBool: MutableLiveData<Boolean> = MutableLiveData()

    fun getWeatherInfo(location: String, unit: String) = viewModelScope.launch(Dispatchers.IO) {
        weatherInfo.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)){

                val apiResult = getWeatherInfoUseCase.execute(location, unit)
                weatherInfo.postValue(apiResult)
            } else {
                weatherInfo.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception){
            weatherInfo.postValue(Resource.Error(e.message.toString()))
        }
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}