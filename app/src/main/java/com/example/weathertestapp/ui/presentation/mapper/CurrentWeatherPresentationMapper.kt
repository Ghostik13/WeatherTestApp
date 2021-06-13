package com.example.weathertestapp.ui.presentation.mapper

import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation

fun CurrentWeatherDomain.toPresentationModel() = CurrentWeatherPresentation(
    temp = (temp-273.15).toInt(),
    humidity = humidity,
//    rain = rain,
    pressure = pressure,
    windSpeed = windSpeed,
    windDirection = windDirection,
    description = description
)