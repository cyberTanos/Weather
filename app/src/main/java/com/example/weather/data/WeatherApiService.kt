package com.example.weather.data

import com.example.weather.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET("timeline/{city}")
    suspend fun getWeathers(
        @Path("city") city: String,
        @Query("key") key: String = "YC9FLL5YUFWM93T6ALTJ25XYC",
        @Query("unitGroup") unitGroup: String = "metric",
        @Query("lang") lang: String = "ru"
    ): WeatherResponse
}
