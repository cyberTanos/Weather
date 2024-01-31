package com.example.weather.data.response


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("address") val address: String,
    @SerializedName("currentConditions") val currentConditions: CurrentConditions,
    @SerializedName("description") val description: String,
    @SerializedName("resolvedAddress") val resolvedAddress: String
) {
    data class CurrentConditions(
        @SerializedName("cloudcover") val cloudcover: Double,
        @SerializedName("conditions") val conditions: String,
        @SerializedName("datetime") val datetime: String,
        @SerializedName("datetimeEpoch") val datetimeEpoch: Int,
        @SerializedName("dew") val dew: Double,
        @SerializedName("feelslike") val feelslike: Double,
        @SerializedName("humidity") val humidity: Double,
        @SerializedName("icon") val icon: String,
        @SerializedName("moonphase") val moonphase: Double,
        @SerializedName("precip") val precip: Double,
        @SerializedName("precipprob") val precipprob: Double,
        @SerializedName("preciptype") val preciptype: Any,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("snow") val snow: Double,
        @SerializedName("snowdepth") val snowdepth: Int,
        @SerializedName("solarenergy") val solarenergy: Double,
        @SerializedName("solarradiation") val solarradiation: Int,
        @SerializedName("source") val source: String,
        @SerializedName("stations") val stations: List<String>,
        @SerializedName("sunrise") val sunrise: String,
        @SerializedName("sunriseEpoch") val sunriseEpoch: Int,
        @SerializedName("sunset") val sunset: String,
        @SerializedName("sunsetEpoch") val sunsetEpoch: Int,
        @SerializedName("temp") val temp: Double,
        @SerializedName("uvindex") val uvindex: Int,
        @SerializedName("visibility") val visibility: Double,
        @SerializedName("winddir") val winddir: Int,
        @SerializedName("windgust") val windgust: Double,
        @SerializedName("windspeed") val windspeed: Double
    )
}
