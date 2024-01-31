package com.example.weather.domain

import android.icu.text.SimpleDateFormat
import com.example.weather.R
import com.example.weather.data.response.WeatherResponse
import com.example.weather.model.entity.WeatherEntity
import com.example.weather.model.presentation.Weather
import java.util.Date

object WeatherMap {

    fun mapToEntity(response: WeatherResponse): WeatherEntity {
        return WeatherEntity(
            city = mapCity(response.resolvedAddress),
            temp = mapTemp(response.currentConditions.temp),
            image = response.currentConditions.icon,
            time = getTime(),
            description = response.description,
            humidity = mapHumidity(response.currentConditions.humidity),
            windSpeed = mapWind(response.currentConditions.windspeed),
            pressure = mapPressure(response.currentConditions.pressure)
        )
    }

    fun map(entity: WeatherEntity): Weather {
        return Weather(
            city = entity.city,
            temp = entity.temp,
            image = getImage(entity.image),
            time = entity.time,
            description = entity.description,
            humidity = entity.humidity,
            windSpeed = entity.windSpeed,
            pressure = entity.pressure
        )
    }

    private fun mapCity(address: String): String {
        return address.split(",").first()
    }

    private fun mapTemp(temp: Double): String {
        return temp.toInt().toString() + "°"
    }

    private fun mapWind(windspeed: Double): String {
        return windspeed.toInt().toString() + "м/с"
    }

    private fun mapPressure(pressure: Int): String {
        return pressure.toString() + "мм рт.ст."
    }

    private fun getImage(icon: String): Int {
        return when (icon) {
            "snow" -> R.drawable.snow
            "rain" -> R.drawable.rain
            "thunder-rain" -> R.drawable.thunder_rain
            "cloudy" -> R.drawable.cloudy
            "showers-day" -> R.drawable.showers_day
            "showers-night" -> R.drawable.showers_night
            "wind" -> R.drawable.wind
            "partly-cloudy-day" -> R.drawable.partly_cloudy_day
            "partly-cloudy-night" -> R.drawable.partly_cloudy_night
            "clear-day" -> R.drawable.clear_day
            "clear-night" -> R.drawable.clear_night
            "fog" -> R.drawable.fog
            "snow-showers-day" -> R.drawable.snow_shower_day
            "snow-showers-night" -> R.drawable.weather_snow_night
            else -> R.drawable.error_not_icon
        }
    }

    private fun getTime(): String {
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
    }

    private fun mapHumidity(humidity: Double): String {
        return humidity.toInt().toString() + "%"
    }
}
