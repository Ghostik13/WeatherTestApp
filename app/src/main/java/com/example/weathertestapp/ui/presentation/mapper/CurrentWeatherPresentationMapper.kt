package com.example.weathertestapp.ui.presentation.mapper

import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.toWeatherIcon
import com.example.weathertestapp.toWindDirection
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation

fun CurrentWeatherDomain.toPresentationModel() = CurrentWeatherPresentation(
    city = city,
    temp = (temp-273.15).toInt(),
    humidity = humidity,
    rain = rain,
    pressure = pressure,
    windSpeed = ((windSpeed*3600)/1000).toInt(),
    windDirection = windDirection.toWindDirection(),
    description = description,
    icon = icon.toWeatherIcon()
)

fun CurrentWeatherDB.toPresentationModel() = CurrentWeatherPresentation(
    city = city,
    temp = (temp-273.15).toInt(),
    humidity = humidity,
    rain = rain,
    pressure = pressure,
    windSpeed = ((windSpeed*3600)/1000).toInt(),
    windDirection = windDirection.toWindDirection(),
    description = description,
    icon = icon.toWeatherIcon()
)