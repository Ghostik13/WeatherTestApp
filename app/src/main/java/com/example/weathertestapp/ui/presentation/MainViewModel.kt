package com.example.weathertestapp.ui.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.ui.presentation.mapper.toPresentation
import com.example.weathertestapp.ui.presentation.mapper.toPresentationModel
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation
import com.example.weathertestapp.ui.presentation.model.ForecastPresentation
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<CurrentWeatherPresentation>()
    val weather: LiveData<CurrentWeatherPresentation> = _weather

    companion object {
        private val _city = MutableLiveData<String>()
    }
    val city: LiveData<String> = _city

    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun initCity(name: String) {
        _city.value = name
    }

    fun getCurrentWeather(name: String) {
        runBlocking {
            val weather = async {
                try {
                    val w = repository.getCurrentWeather(name)
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

    fun getCurrentWeatherDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDb = repository.getCurrentWeather()
            withContext(Dispatchers.Main) {
                _weather.value = weatherDb?.toPresentationModel()
            }
        }
    }

    private val _forecast = MutableLiveData<ForecastPresentation>()
    val forecast: LiveData<ForecastPresentation> = _forecast

    fun getForecastDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val forecastDB = repository.getForecast()
            withContext(Dispatchers.Main) {
                if(forecastDB != null) {
                    _forecast.value = forecastDB.toPresentation()
                }
            }
        }
    }

    fun getForecast(name: String) {
        runBlocking {
            val forecast = async {
                try {
                    val f = repository.getForecast(name)
                    _forecast.value = f.toPresentation()
                    loading.postValue(true)
                } catch (e: Exception) {
                    Log.d("INTERNET", "LOST")
                    withContext(Dispatchers.IO) {
                        _forecast.postValue(
                            repository.getForecast()?.toPresentation()
                        )
                        loading.postValue(false)
                    }
                }
            }
            forecast.await()
        }
    }
}
