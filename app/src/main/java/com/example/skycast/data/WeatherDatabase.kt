
package com.example.skycast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.skycast.model.Favorite


@Database(entities = [Favorite::class], version = 3, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}