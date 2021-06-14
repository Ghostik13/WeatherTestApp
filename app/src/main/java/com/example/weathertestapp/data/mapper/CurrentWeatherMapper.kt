package com.example.weathertestapp.data.mapper

import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.CurrentWeatherRemote
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import java.text.SimpleDateFormat
import java.util.*

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

fun CurrentWeatherRemote.toDbModel() = CurrentWeatherDB(
    id = 1,
    city = name,
    timezone = timezone,
    temp = main.temp,
    humidity = main.humidity,
    rain = rain?.high,
    pressure = main.pressure,
    windSpeed = wind.speed,
    windDirection = wind.deg,
    description = weather[0].description,
    time = Calendar.getInstance().time.toString()
)