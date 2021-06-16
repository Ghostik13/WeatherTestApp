package com.example.weathertestapp.data.mapper

import com.example.weathertestapp.data.model.*
import com.example.weathertestapp.domain.model.CurrentWeatherDomain
import com.example.weathertestapp.domain.model.ForecastDomain
import java.util.*

fun CurrentWeatherRemote.toDomainModel() = CurrentWeatherDomain(
    id = id,
    city = name,
    timezone = timezone,
    temp = main.temp,
    humidity = main.humidity,
    rain = rain?.high,
    pressure = main.pressure,
    windSpeed = wind.speed,
    windDirection = wind.deg,
    description = weather[0].description,
    icon = weather[0].icon
)

fun CurrentWeatherRemote.toDbModel() = CurrentWeatherDB(
    id = 1,
    city = name,
    timezone = timezone,
    temp = main.temp,
    humidity = main.humidity,
    rain = rain?.high,
    pressure = main.pressure,
    windSpeed = wind.speed,
    windDirection = wind.deg,
    description = weather[0].description,
    icon = weather[0].icon
)

fun List<Model>.toModelDb(): List<ModelDb> {
    return List(this.size) {
        ModelDb(
            dt_txt = this[it].dt_txt,
            temp = this[it].main.temp,
            description = this[it].weather[0].description,
            icon = this[it].weather[0].icon,
        )
    }
}

fun ForecastRemote.toDbModel() = ForecastDB(
    id = 1,
    city = city.name,
    list =list.toModelDb()
)

fun ForecastRemote.toDomain() = ForecastDomain(
    city = city.name,
    list =list.toModelDb()
)