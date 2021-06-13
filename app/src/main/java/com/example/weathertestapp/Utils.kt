package com.example.weathertestapp

import java.util.*

fun String.firstToUpperCase(): String =
    substring(0, 1).toUpperCase(Locale.getDefault()) + substring(1, length)

fun Int.toWindDirection(): String {
    when(this) {
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