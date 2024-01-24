package com.example.weather.weather

import com.example.weather.model.presentation.Weather

interface WeatherAction {

    data class SearchCity(
        val city: String
    ) : WeatherAction
}

interface WeatherState {

    object Loading : WeatherState

    data class Success(
        val weathers: List<Weather>
    ) : WeatherState

    data class Error(
        val errorMessage: String
    ) : WeatherState
}
