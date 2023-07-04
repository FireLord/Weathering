package com.firelord.weathering.presentation.di

import com.firelord.weathering.domain.repository.WeatherRepository
import com.firelord.weathering.domain.usecase.GetWeatherInfoUseCase
import com.firelord.weathering.domain.usecase.SaveWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetWeatherInfoUseCase(
        weatherRepository: WeatherRepository
    ):GetWeatherInfoUseCase{
        return GetWeatherInfoUseCase(weatherRepository)
    }

    @Singleton
    @Provides
    fun provideSaveWeatherInfoUseCase(
        weatherRepository: WeatherRepository
    ):SaveWeatherUseCase{
        return SaveWeatherUseCase(weatherRepository)
    }
}