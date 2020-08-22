package com.firelord.weathering.intro.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firelord.weathering.core.data.Constants.CURRENT_WEATHER_ID
import com.firelord.weathering.intro.data.db.response.RemoteFetch

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: RemoteFetch)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherDb(): LiveData<RemoteFetch>
}