package com.example.weathertestapp.data

import com.example.weathertestapp.Constants.Companion.API_KEY
import com.example.weathertestapp.data.model.CurrentWeatherRemote
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.remote.WeatherApi

class WeatherRepositoryImpl(private val api: WeatherApi): WeatherRepository {

    override suspend fun getCurrentWeather(city: String): CurrentWeatherRemote {
        return api.getCurrentWeather("Minsk", API_KEY)
    }

    override suspend fun getForecast(): ForecastRemote {
        return api.getForecast("Minsk", API_KEY)
    }
}