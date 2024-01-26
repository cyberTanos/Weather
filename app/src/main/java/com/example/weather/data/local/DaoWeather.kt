package com.example.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.entity.WeatherEntity

@Dao
interface DaoWeather {
    @Query("SELECT * FROM weatherentity ORDER BY time DESC")
    suspend fun getAll(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntity: WeatherEntity)
}
