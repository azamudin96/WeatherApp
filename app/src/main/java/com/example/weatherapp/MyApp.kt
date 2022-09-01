package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.RetrofitModule
import com.example.weatherapp.di.apiModule
import com.example.weatherapp.di.repositoryModule
import com.example.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)

            modules(listOf(apiModule,repositoryModule,viewModelModule,RetrofitModule))
        }
    }

}