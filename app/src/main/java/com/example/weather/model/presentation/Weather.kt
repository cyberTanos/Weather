package com.example.weather.model.presentation

import androidx.annotation.DrawableRes

data class Weather(
    val city: String,
    val temp: String,
    @DrawableRes val image: Int,
    val time: String
)
