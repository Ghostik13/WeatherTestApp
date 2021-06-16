package com.example.weathertestapp.ui.presentation.mapper

import com.example.weathertestapp.data.model.ForecastDB
import com.example.weathertestapp.domain.model.ForecastDomain
import com.example.weathertestapp.ui.presentation.model.ForecastPresentation

fun ForecastDomain.toPresentation() = ForecastPresentation(
    city = city,
    list = list
)

fun ForecastDB.toPresentation() = ForecastPresentation(
    city = city,
    list = list
)