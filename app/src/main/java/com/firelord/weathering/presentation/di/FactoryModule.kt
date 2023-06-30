package com.firelord.weathering.presentation.di

import android.app.Application
import com.firelord.weathering.domain.usecase.GetWeatherInfoUseCase
import com.firelord.weathering.presentation.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideWeatherViewModelFactory(
        app: Application,
        getWeatherInfoUseCase: GetWeatherInfoUseCase
    ):WeatherViewModelFactory{
        return WeatherViewModelFactory(app, getWeatherInfoUseCase)
    }
}