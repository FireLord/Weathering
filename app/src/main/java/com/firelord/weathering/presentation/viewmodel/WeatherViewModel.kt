package com.firelord.weathering.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.NetworkCheck.checkNetwork
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
    var weatherUnit: MutableLiveData<String> = MutableLiveData("metric")

    fun getWeatherInfo(location: String, unit: String) = viewModelScope.launch(Dispatchers.IO) {
        weatherInfo.postValue(Resource.Loading())
        try {
            if (checkNetwork(app)){

                val apiResult = getWeatherInfoUseCase.execute(location, unit)
                weatherInfo.postValue(apiResult)
            } else {
                weatherInfo.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception){
            weatherInfo.postValue(Resource.Error(e.message.toString()))
        }
    }
}