package com.example.weather.model.presentation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val city: String,
    val temp: String,
    @DrawableRes val image: Int,
    val time: String
) : Parcelable
