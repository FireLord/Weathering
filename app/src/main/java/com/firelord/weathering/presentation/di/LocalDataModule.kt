package com.firelord.weathering.presentation.di

import com.firelord.weathering.data.Repository.WeatherRepositoryImpl
import com.firelord.weathering.data.Repository.dataSource.WeatherLocalDataSource
import com.firelord.weathering.data.Repository.dataSourceImpl.WeatherLocalDataSourceImpl
import com.firelord.weathering.data.Repository.dataSourceImpl.WeatherRemoteDataSourceImpl
import com.firelord.weathering.data.db.RemoteFetchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(remoteFetchDao: RemoteFetchDao):WeatherLocalDataSource{
        return WeatherLocalDataSourceImpl(remoteFetchDao)
    }
}