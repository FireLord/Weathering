package com.firelord.weathering.domain.usecase

import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.domain.repository.WeatherRepository

class GetWeatherInfoUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(): Resource<RemoteFetch>{
        return weatherRepository.getWeatherInfo()
    }
}