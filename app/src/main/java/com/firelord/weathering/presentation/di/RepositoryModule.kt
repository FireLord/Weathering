package com.firelord.weathering.presentation.di

import com.firelord.weathering.data.Repository.WeatherRepositoryImpl
import com.firelord.weathering.data.Repository.dataSource.WeatherLocalDataSource
import com.firelord.weathering.data.Repository.dataSource.WeatherRemoteDataSource
import com.firelord.weathering.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherRemoteDataSource: WeatherRemoteDataSource,
        weatherLocalDataSource: WeatherLocalDataSource
    ):WeatherRepository{
        return WeatherRepositoryImpl(
            weatherRemoteDataSource,
            weatherLocalDataSource)
    }
}