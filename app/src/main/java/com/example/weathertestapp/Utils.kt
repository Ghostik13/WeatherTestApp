package com.example.weathertestapp

fun String.firstToUpperCase(): String =
    substring(0, 1).toUpperCase() + substring(1, length)