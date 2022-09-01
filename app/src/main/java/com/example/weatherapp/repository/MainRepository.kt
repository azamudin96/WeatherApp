package com.example.weatherapp.repository

import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.models.request.ApiServices
import retrofit2.Response

class MainRepository(private val apiServices: ApiServices) {
    suspend fun repoCurrentWeather(cityName: String): Response<WeatherResponse> {
        return apiServices.getCurrentWeather(cityName)
    }

    suspend fun repoGetForecastWeather(cityName: String,days:Int): Response<WeatherResponse> {
        return apiServices.getForecastWeather(cityName,days)
    }
}