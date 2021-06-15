package com.example.weathertestapp.di

import android.app.Application
import androidx.room.Room
import com.example.weathertestapp.Constants
import com.example.weathertestapp.data.WeatherDao
import com.example.weathertestapp.data.WeatherDatabase
import com.example.weathertestapp.data.WeatherRepositoryImpl
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.remote.WeatherApi
import com.example.weathertestapp.ui.presentation.MainViewModel
import okhttp3.Cache
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val currentViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repository = module {
    fun provideRepository(api: WeatherApi, dao: WeatherDao): WeatherRepositoryImpl {
        return WeatherRepositoryImpl(api, dao)
    }
    single<WeatherRepository> { provideRepository(get(), get()) }
}

val apiModule = module {
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    single { provideWeatherApi(get()) }
}

val netModule = module {
    fun provideRetrofit(): Retrofit? {
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

val databaseModule = module {

    fun provideDatabase(application: Application): WeatherDatabase {
        return Room.databaseBuilder(application, WeatherDatabase::class.java, "weather")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}