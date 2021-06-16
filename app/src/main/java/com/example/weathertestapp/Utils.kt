package com.example.weathertestapp

import java.util.*

fun String.firstToUpperCase(): String =
    substring(0, 1).toUpperCase(Locale.getDefault()) + substring(1, length)

fun Int.toWindDirection(): String {
    return when (this) {
        in (0..20) -> "N"
        in (10..80) -> "NE"
        in (80..100) -> "E"
        in (100..170) -> "SE"
        in (170..190) -> "S"
        in (190..260) -> "SW"
        in (260..280) -> "W"
        in (280..350) -> "NW"
        in (350..360) -> "N"
        else -> ""
    }
}

fun String.toWeatherIcon(): Int {
    return when (this) {
        "01d", "01n" -> R.drawable.ic_clear_sky
        "02d", "02n" -> R.drawable.ic_few_clouds
        "03d", "03n" -> R.drawable.ic_scattered_clouds
        "04d", "04n" -> R.drawable.ic_broken_clouds
        "50d", "50n" -> R.drawable.ic_mist
        "10d", "10n" -> R.drawable.ic_rain
        "09d", "09n" -> R.drawable.ic_shower_rain
        "13d", "13n" -> R.drawable.ic_snow
        "11d", "11n" -> R.drawable.ic_thunderstorm
        else -> R.drawable.ic_thunderstorm
    }
}

var cityName: String = ""