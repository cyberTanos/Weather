package com.example.weather.weather

import androidx.lifecycle.ViewModel
import com.example.weather.model.presentation.Weather
import com.example.weather.weather.WeatherAction.SearchCity
import com.example.weather.weather.WeatherState.Error
import com.example.weather.weather.WeatherState.Loading
import com.example.weather.weather.WeatherState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class WeatherVM @Inject constructor(

) : ViewModel() {

    val state = MutableStateFlow<WeatherState>(Loading)

    fun doAction(action: WeatherAction) {
        when (action) {
            is SearchCity -> fetchCity(action.city)
        }
    }

    private fun fetchCity(city: String) {
        if (city.length >= 3) {
            state.value = Success(
                listOf(
                    Weather(city = city, temp = "222")
                )
            )
        } else {
            state.value = Error(
                errorMessage = "Введите город"
            )
        }
    }
}
