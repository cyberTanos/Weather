package com.example.weather.weather.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.weather.content.ContentFragment.Companion.KEY
import com.example.weather.weather.main.WeatherAction.InitScreen
import com.example.weather.weather.main.WeatherAction.OnClickItem
import com.example.weather.weather.main.WeatherAction.SearchCity
import com.example.weather.weather.main.WeatherEffect.ToNavigationContent
import com.example.weather.weather.main.WeatherState.Empty
import com.example.weather.weather.main.WeatherState.Error
import com.example.weather.weather.main.WeatherState.Loading
import com.example.weather.weather.main.WeatherState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private lateinit var binding: FragmentWeatherBinding
    private val adapter = WeatherAdapter { weather ->
        vm.doAction(OnClickItem(weather))
    }
    private val vm: WeatherVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        observeState()
        observeEffect()
        bindUI()
        binding.searchCityView.setOnClickListener {
            vm.doAction(SearchCity(binding.searchCityView.text.toString()))
        }

        vm.doAction(InitScreen)

        return binding.root
    }

    private fun bindUI() {
        binding.recyclerWeather.adapter = adapter
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.state.collect { state ->
                    binding.imageStateEmpty.isVisible = state is Empty
                    binding.isLoading.isVisible = state is Loading
                    when (state) {
                        is Error -> binding.errorCity.text = state.errorMessage
                        is Success -> adapter.submitList(state.weathers)
                    }
                }
            }
        }
    }

    private fun observeEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.effect.collect { effect ->
                    when (effect) {
                        is ToNavigationContent -> findNavController().navigate(
                            R.id.to_contentFragment,
                            bundleOf(KEY to effect.weather)
                        )
                    }
                }
            }
        }
    }
}
