package com.example.weathertestapp.domain

import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.ForecastDB
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.domain.model.ForecastDomain

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): CurrentWeatherDomain
    suspend fun getForecast(city: String): ForecastDomain
    fun getCurrentWeather(): CurrentWeatherDB?
    fun getForecast(): ForecastDB?
}