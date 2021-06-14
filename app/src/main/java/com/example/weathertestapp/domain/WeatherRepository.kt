package com.example.weathertestapp.domain

import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.model.CurrentWeatherDomain

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): CurrentWeatherDomain
    suspend fun getForecast(city: String): ForecastRemote
    fun getCurrentWeather(): CurrentWeatherDB?
}