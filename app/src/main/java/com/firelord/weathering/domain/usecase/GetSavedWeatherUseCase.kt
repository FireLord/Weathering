package com.firelord.weathering.domain.usecase

import com.firelord.weathering.data.model.Main
import com.firelord.weathering.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetSavedWeatherUseCase(private val weatherRepository: WeatherRepository) {
    fun execute(): Flow<List<Main>>{
        return weatherRepository.getSavedWeather()
    }
}