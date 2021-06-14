package com.example.weathertestapp

import java.util.*

fun String.firstToUpperCase(): String =
    substring(0, 1).toUpperCase(Locale.getDefault()) + substring(1, length)

fun Int.toWindDirection(): String {
    when (this) {
        in (0..20) -> return "N"
        in (10..80) -> return "NE"
        in (80..100) -> return "E"
        in (100..170) -> return "SE"
        in (170..190) -> return "S"
        in (190..260) -> return "SW"
        in (260..280) -> return "W"
        in (280..350) -> return "NW"
        in (350..360) -> return "N"
    }
    return ""
}

fun String.toWeatherIcon(): Int {
    when (this) {
        "01d", "01n" -> return R.drawable.ic_clear_sky
        "02d", "02n" -> return R.drawable.ic_few_clouds
        "03d", "03n" -> return R.drawable.ic_scattered_clouds
        "04d", "04n" -> return R.drawable.ic_broken_clouds
        "50d", "50n" -> return R.drawable.ic_mist
        "10d", "10n" -> return R.drawable.ic_rain
        "09d", "09n" -> return R.drawable.ic_shower_rain
        "13d", "13n" -> return R.drawable.ic_snow
        "11d", "11n" -> return R.drawable.ic_thunderstorm
    }
    return R.drawable.ic_snow
}