package com.example.weatherapp.models.request

import com.example.weatherapp.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String
    ): Response<WeatherResponse>

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") cityName: String,
        @Query("days") days: Int
    ): Response<WeatherResponse>
}