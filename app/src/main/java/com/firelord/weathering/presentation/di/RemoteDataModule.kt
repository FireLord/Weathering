package com.firelord.weathering.presentation.di

import com.firelord.weathering.data.Repository.dataSource.WeatherRemoteDataSource
import com.firelord.weathering.data.Repository.dataSourceImpl.WeatherRemoteDataSourceImpl
import com.firelord.weathering.data.api.WeatherAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        weatherAPIService: WeatherAPIService
    ):WeatherRemoteDataSource{
        return WeatherRemoteDataSourceImpl(weatherAPIService)
    }
}