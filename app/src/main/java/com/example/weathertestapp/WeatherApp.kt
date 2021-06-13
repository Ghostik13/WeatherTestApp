package com.example.weathertestapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.weathertestapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    currentViewModelModule,
                    weekViewModelModule,
                    repository,
                    netModule,
                    apiModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}