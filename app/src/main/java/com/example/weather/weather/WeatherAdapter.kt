package com.example.weather.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemWeatherBinding
import com.example.weather.model.presentation.Weather
import com.example.weather.weather.WeatherAdapter.WeatherVH

class WeatherAdapter() : ListAdapter<Weather, WeatherVH>(Differ) {

    class WeatherVH(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.nameCityView.text = weather.city
            binding.tempCityView.text = weather.temp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemWeatherBinding.inflate(inflate, parent, false)
        return WeatherVH(binding)
    }

    override fun onBindViewHolder(holder: WeatherVH, position: Int) {
        val itemWeather = getItem(position)
        holder.bind(itemWeather)
    }

    object Differ : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
    }
}
