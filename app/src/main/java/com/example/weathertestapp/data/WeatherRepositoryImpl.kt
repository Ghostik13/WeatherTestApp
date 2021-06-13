package com.example.weathertestapp.data

import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.remote.WeatherApi

class WeatherRepositoryImpl(private val api: WeatherApi): WeatherRepository {
}