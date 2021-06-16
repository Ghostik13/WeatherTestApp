package com.example.weathertestapp.domain.model

import com.example.weathertestapp.data.model.ModelDb

data class ForecastDomain(
    val city: String,
    val list: List<ModelDb>
)