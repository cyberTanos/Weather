package com.example.weather.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey val city: String,
    val temp: String,
    val image: String,
    val time: String,
    val description: String,
    val humidity: String,
    val windSpeed: String,
    val pressure: String
)
