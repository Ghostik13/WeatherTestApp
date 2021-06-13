package com.example.weathertestapp.data

import com.example.weathertestapp.Constants.Companion.API_KEY
import com.example.weathertestapp.data.mapper.toDomainModel
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.remote.WeatherApi

class WeatherRepositoryImpl(private val api: WeatherApi): WeatherRepository {

    override suspend fun getCurrentWeather(city: String): CurrentWeatherDomain {
        return api.getCurrentWeather("Minsk", API_KEY).toDomainModel()
    }

    override suspend fun getForecast(): ForecastRemote {
        return api.getForecast("Minsk", API_KEY)
    }
}