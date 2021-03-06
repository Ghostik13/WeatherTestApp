package com.example.weathertestapp.data.model

data class CurrentWeatherRemote(
    val base: String,
    val clouds: CurrentClouds,
    val cod: Int,
    val coord: CurrentCoord,
    val dt: Int,
    val id: Int,
    val main: CurrentMain,
    val name: String,
    val sys: CurrentSys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<CurrentWeather>,
    val wind: CurrentWind,
    val rain: CurrentRain?
)

data class CurrentMain(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class CurrentRain(
    val high: Double?
)

data class CurrentSys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

data class CurrentCoord(
    val lat: Double,
    val lon: Double
)

data class CurrentWeather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class CurrentClouds(
    val all: Int
)

data class CurrentWind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)
