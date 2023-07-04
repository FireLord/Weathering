package com.firelord.weathering.presentation.di

import android.app.Application
import androidx.room.Room
import com.firelord.weathering.data.db.RemoteFetchDao
import com.firelord.weathering.data.db.RemoteFetchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(app:Application):RemoteFetchDatabase{
        return Room.databaseBuilder(app,RemoteFetchDatabase::class.java,"weather_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(remoteFetchDatabase: RemoteFetchDatabase):RemoteFetchDao{
        return remoteFetchDatabase.RemoteFetchDao()
    }
}