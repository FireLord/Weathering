package com.firelord.weathering.domain.usecase

import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.domain.repository.WeatherRepository

class GetSearchedWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(searchQuery: String) : Resource<RemoteFetch> {
        return weatherRepository.getSearchedWeather(searchQuery)
    }
}