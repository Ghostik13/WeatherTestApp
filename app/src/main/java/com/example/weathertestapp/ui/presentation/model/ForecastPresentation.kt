package com.example.weathertestapp.ui.presentation.model

import com.example.weathertestapp.data.model.ModelDb

data class ForecastPresentation(
    val city: String,
    val list: List<ModelDb>
)

