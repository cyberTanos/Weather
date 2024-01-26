package com.example.weather.domain

import com.example.weather.data.WeatherApiService
import com.example.weather.data.local.DaoWeather
import com.example.weather.model.presentation.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApiService,
    private val daoWeather: DaoWeather
) {

    suspend fun getWeathers(): List<Weather> {
        val weatherFromDB = daoWeather.getAll()
        val weather = weatherFromDB.map { weatherEntity ->
            WeatherMap.map(weatherEntity)
        }
        return weather
    }

    suspend fun addWeather(city: String): List<Weather> {
        val weatherResponse = api.getWeathers(city)
        val weatherEntity = WeatherMap.mapToEntity(weatherResponse)
        daoWeather.insert(weatherEntity)
        val weatherFromDB = daoWeather.getAll()
        val weather = weatherFromDB.map { weatherEntity ->
            WeatherMap.map(weatherEntity)
        }
        return weather
    }
}
