package com.example.weathertestapp.ui.presentation.mapper

import com.example.weathertestapp.*
import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation

fun CurrentWeatherDomain.toPresentationModel() = CurrentWeatherPresentation(
    city = city,
    temp = temp.toCelsius(),
    humidity = humidity,
    rain = rain,
    pressure = pressure,
    windSpeed = windSpeed.toIntSpeed(),
    windDirection = windDirection.toWindDirection(),
    description = description,
    icon = icon.toWeatherIcon()
)

fun CurrentWeatherDB.toPresentationModel() = CurrentWeatherPresentation(
    city = city,
    temp = temp.toCelsius(),
    humidity = humidity,
    rain = rain,
    pressure = pressure,
    windSpeed = windSpeed.toIntSpeed(),
    windDirection = windDirection.toWindDirection(),
    description = description,
    icon = icon.toWeatherIcon()
)