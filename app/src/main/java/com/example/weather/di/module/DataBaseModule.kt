package com.example.weather.di.module

import android.content.Context
import androidx.room.Room
import com.example.weather.data.local.DaoWeather
import com.example.weather.data.local.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDataBase {
        return Room.databaseBuilder(
            context,
            WeatherDataBase::class.java, "dataBase"
        ).build()
    }

    @Provides
    fun provideDao(db: WeatherDataBase): DaoWeather {
        return db.daoWeather()
    }
}
