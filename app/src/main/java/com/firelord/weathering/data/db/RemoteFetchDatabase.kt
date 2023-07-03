package com.firelord.weathering.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.firelord.weathering.data.model.RemoteFetch


@Database(
    entities = [RemoteFetch::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RemoteFetchDatabase: RoomDatabase() {
    abstract fun RemoteFetchDao():RemoteFetchDao
}