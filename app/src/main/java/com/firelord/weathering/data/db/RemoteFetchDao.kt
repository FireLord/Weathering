package com.firelord.weathering.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.firelord.weathering.data.model.RemoteFetch

@Dao
interface RemoteFetchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteFetch: RemoteFetch)
}