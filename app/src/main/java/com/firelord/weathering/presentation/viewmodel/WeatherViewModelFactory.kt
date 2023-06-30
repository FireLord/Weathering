package com.firelord.weathering.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firelord.weathering.domain.usecase.GetWeatherInfoUseCase

class WeatherViewModelFactory(
    private val app: Application,
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(
            app,
            getWeatherInfoUseCase
        ) as T
    }
}