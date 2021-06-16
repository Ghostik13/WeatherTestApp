package com.example.weathertestapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ForecastConverter {

    @TypeConverter
    fun fromModelDb(models: List<ModelDb?>?): String? {
        if (models == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ModelDb?>?>() {}.type
        return gson.toJson(models, type)
    }

    @TypeConverter
    fun toModelDb(modelString: String?): List<ModelDb>? {
        if (modelString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ModelDb?>?>() {}.type
        return gson.fromJson<List<ModelDb>>(modelString, type)
    }
}