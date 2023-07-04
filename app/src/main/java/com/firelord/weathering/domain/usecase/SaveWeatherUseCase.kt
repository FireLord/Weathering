package com.firelord.weathering.domain.usecase

import com.firelord.weathering.data.model.Main
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.domain.repository.WeatherRepository

class SaveWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(remoteFetch: RemoteFetch) = weatherRepository.saveWeather(remoteFetch)
}