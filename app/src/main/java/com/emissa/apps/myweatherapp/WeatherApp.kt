package com.emissa.apps.myweatherapp

import android.app.Application
import android.app.SearchManager
import android.content.Intent
import android.util.Log
import com.emissa.apps.myweatherapp.di.networkModule
import com.emissa.apps.myweatherapp.di.viewModelModule
import com.emissa.apps.myweatherapp.viewmodel.WeatherViewModel
import com.emissa.apps.myweatherapp.views.SearchDialog
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            // add all modules needed for the project here to start koin
            modules(listOf(networkModule, viewModelModule))
        }
    }
}