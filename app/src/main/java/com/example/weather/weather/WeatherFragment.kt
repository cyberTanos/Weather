package com.example.weather.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.weather.WeatherAction.SearchCity
import com.example.weather.weather.WeatherState.Error
import com.example.weather.weather.WeatherState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private lateinit var binding: FragmentWeatherBinding
    private val adapter = WeatherAdapter()
    private val vm: WeatherVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        observeState()
        bindUI()
        binding.searchCityView.setOnClickListener {
            vm.doAction(SearchCity(binding.searchCityView.text.toString()))
        }

        return binding.root
    }

    private fun bindUI() {
        binding.recyclerWeather.adapter = adapter
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.state.collect { state ->
                    when (state) {
                        is Error -> binding.errorCity.text = state.errorMessage
                        is Success -> adapter.submitList(state.weathers)
                    }
                }
            }

        }
    }
}
