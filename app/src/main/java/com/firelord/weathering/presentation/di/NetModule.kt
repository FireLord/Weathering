package com.firelord.weathering.presentation.di

import com.firelord.weathering.data.Constants.API_KEY
import com.firelord.weathering.data.api.WeatherAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_KEY)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherAPIService(retrofit: Retrofit):WeatherAPIService{
        return retrofit.create(WeatherAPIService::class.java)
    }
}