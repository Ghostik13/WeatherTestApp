package com.example.weathertestapp.di

import android.app.Application
import com.example.weathertestapp.Constants
import com.example.weathertestapp.data.WeatherRepositoryImpl
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.remote.WeatherApi
import com.example.weathertestapp.ui.presentation.currentWeatherView.CurrentWeatherViewModel
import com.example.weathertestapp.ui.presentation.forecastView.ForecastViewModel
import okhttp3.Cache
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val currentViewModelModule = module {
    viewModel { CurrentWeatherViewModel(get()) }
}

val weekViewModelModule = module {
    viewModel { ForecastViewModel(get()) }
}

val repository = module {
    fun provideRepository(api: WeatherApi): WeatherRepositoryImpl {
        return WeatherRepositoryImpl(api)
    }
    single<WeatherRepository> { provideRepository(get()) }
}

val apiModule = module {
    fun provideGiphyApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    single { provideGiphyApi(get()) }
}

val netModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    single { provideRetrofit() }
    single { provideCache(get()) }
}