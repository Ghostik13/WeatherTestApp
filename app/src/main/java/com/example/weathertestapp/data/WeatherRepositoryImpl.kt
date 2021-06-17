package com.example.weathertestapp.data

import android.util.Log
import com.example.weathertestapp.Constants.Companion.API_KEY
import com.example.weathertestapp.data.mapper.toDbModel
import com.example.weathertestapp.data.mapper.toDomain
import com.example.weathertestapp.data.mapper.toDomainModel
import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.ForecastDB
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.domain.model.ForecastDomain
import com.example.weathertestapp.remote.WeatherApi
import java.lang.NullPointerException

class WeatherRepositoryImpl(private val api: WeatherApi, private val weatherDao: WeatherDao) :
    WeatherRepository {

    override suspend fun getCurrentWeather(city: String): CurrentWeatherDomain {
        val weatherDb = api.getCurrentWeather(city, API_KEY).toDbModel()
        weatherDao.insertCurrentWeather(weatherDb)
        return api.getCurrentWeather(city, API_KEY).toDomainModel()
    }

    override suspend fun getForecast(city: String): ForecastDomain {
        val forecastDB = api.getForecast(city, API_KEY).toDbModel()
        weatherDao.insertForecast(forecastDB)
        return api.getForecast(city, API_KEY).toDomain()
    }

    override fun getForecast(): ForecastDB? {
        return weatherDao.getForecast()
    }

    override fun getCurrentWeather(): CurrentWeatherDB? {
        return weatherDao.getCurrentWeather()
    }
}