package com.example.weather.di.module

import com.example.weather.data.WeatherApiService
import com.example.weather.data.local.DaoWeather
import com.example.weather.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(apiService: WeatherApiService, dao: DaoWeather): WeatherRepository {
        val repository = WeatherRepository(apiService, dao)
        return repository
    }
}
