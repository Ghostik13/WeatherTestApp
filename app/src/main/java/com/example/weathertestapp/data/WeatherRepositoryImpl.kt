package com.example.weathertestapp.data

import com.example.weathertestapp.Constants.Companion.API_KEY
import com.example.weathertestapp.data.mapper.toDbModel
import com.example.weathertestapp.data.mapper.toDomainModel
import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.remote.WeatherApi

class WeatherRepositoryImpl(private val api: WeatherApi, private val weatherDao: WeatherDao): WeatherRepository {

    override suspend fun getCurrentWeather(city: String): CurrentWeatherDomain {
        val weatherDb = api.getCurrentWeather(city, API_KEY).toDbModel()
        weatherDao.insertCurrentWeather(weatherDb)
        return api.getCurrentWeather(city, API_KEY).toDomainModel()
    }

    override suspend fun getForecast(city: String): ForecastRemote {
        return api.getForecast(city, API_KEY)
    }

    override fun getCurrentWeather(): CurrentWeatherDB {
        return weatherDao.getCurrentWeather()
    }
}