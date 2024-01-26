package com.example.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.model.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun daoWeather(): DaoWeather
}
