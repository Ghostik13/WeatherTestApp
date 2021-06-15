package com.example.weathertestapp.ui.presentation.currentWeatherView

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.ui.presentation.mapper.toPresentationModel
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation
import kotlinx.coroutines.*
import java.lang.Exception

class CurrentWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<CurrentWeatherPresentation>()
    val weather: LiveData<CurrentWeatherPresentation> = _weather

    val city = MutableLiveData<String>()
    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun getCurrentWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDb = repository.getCurrentWeather()
            withContext(Dispatchers.Main) {
                _weather.value = weatherDb?.toPresentationModel()
            }
        }
        city.observeForever {
            runBlocking {
                val weather = async {
                    try {
                        val w = repository.getCurrentWeather(it)
                        _weather.value = w.toPresentationModel()
                        loading.postValue(true)
                    } catch (e: Exception) {
                        Log.d("INTERNET", "LOST")
                        withContext(Dispatchers.IO) {
                            _weather.postValue(
                                repository.getCurrentWeather()?.toPresentationModel()
                            )
                            loading.postValue(false)
                        }
                    }
                }
                weather.await()
            }
        }
    }
}