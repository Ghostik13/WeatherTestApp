package com.example.weathertestapp.domain

import com.example.weathertestapp.data.model.CurrentWeatherRemote
import com.example.weathertestapp.data.model.ForecastRemote

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): CurrentWeatherRemote
    suspend fun getForecast(): ForecastRemote
}