package com.example.weathertestapp.data.mapper

import com.example.weathertestapp.data.model.CurrentWeatherRemote
import com.example.weathertestapp.domain.model.CurrentWeatherDomain

fun CurrentWeatherRemote.toDomainModel() = CurrentWeatherDomain(
    id = id,
    city = name,
    timezone = timezone,
    temp = main.temp,
    humidity = main.humidity,
    rain = rain?.high,
    pressure = main.pressure,
    windSpeed = wind.speed,
    windDirection = wind.deg,
    description = weather[0].description
)