package com.example.weather.weather.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.WeatherRepository
import com.example.weather.model.presentation.Weather
import com.example.weather.weather.main.WeatherAction
import com.example.weather.weather.main.WeatherAction.InitScreen
import com.example.weather.weather.main.WeatherAction.OnClickItem
import com.example.weather.weather.main.WeatherAction.SearchCity
import com.example.weather.weather.main.WeatherEffect.ToNavigationContent
import com.example.weather.weather.main.WeatherState
import com.example.weather.weather.main.WeatherState.Empty
import com.example.weather.weather.main.WeatherState.Error
import com.example.weather.weather.main.WeatherState.Loading
import com.example.weather.weather.main.WeatherState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherVM @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val state = MutableStateFlow<WeatherState>(Loading)
    private val _effect: Channel<WeatherEffect> = Channel() // создает эффекты, на него нужно вызывать send
    val effect: Flow<WeatherEffect> = _effect.receiveAsFlow() // получает эффекты из _effect, на него мы подписаны в фрагменте

    fun doAction(action: WeatherAction) {
        when (action) {
            is InitScreen -> initScreen()
            is SearchCity -> fetchCity(action.city)
            is OnClickItem -> navigateToContent(action.weather)
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

    private fun navigateToContent(weather: Weather) {
        viewModelScope.launch {
            _effect.send(ToNavigationContent(weather))

        }
    }
}
