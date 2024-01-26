package com.example.weather.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.WeatherRepository
import com.example.weather.weather.WeatherAction.InitScreen
import com.example.weather.weather.WeatherAction.SearchCity
import com.example.weather.weather.WeatherState.Empty
import com.example.weather.weather.WeatherState.Error
import com.example.weather.weather.WeatherState.Loading
import com.example.weather.weather.WeatherState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherVM @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val state = MutableStateFlow<WeatherState>(Loading)

    fun doAction(action: WeatherAction) {
        when (action) {
            is InitScreen -> initScreen()
            is SearchCity -> fetchCity(action.city)
        }
    }

    private fun initScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val weather = repository.getWeathers()
            if (weather.isEmpty()) {
                state.value = Empty
            } else {
                state.value = Success(weather)
            }
        }
    }

    private fun fetchCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (city.length >= 3) {
                state.value = Loading
                val weather = repository.addWeather(city)
                state.value = Success(
                    weather
                )
            } else {
                state.value = Error(
                    errorMessage = "Введите город"
                )
            }
        }
    }
}
