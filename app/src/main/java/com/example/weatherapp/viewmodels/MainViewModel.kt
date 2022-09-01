package com.example.weatherapp.viewmodels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.ErrorResponse
import com.example.weatherapp.models.Resource
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.repository.MainRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.adapter.rxjava2.Result.response


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val cityName = MutableLiveData<String>()

    private val currentWeatherStateFlow =
        MutableStateFlow<Resource<WeatherResponse>>(Resource.empty())

    val currentWeatherState: StateFlow<Resource<WeatherResponse>>
        get() = currentWeatherStateFlow

    fun fetchCurrentWeather(cityName: String) {
        currentWeatherStateFlow.value = Resource.loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = mainRepository.repoCurrentWeather(cityName)
                    if (result.isSuccessful) {
                        currentWeatherStateFlow.value = Resource.success(result.body())
                    } else {
                        val responseError = Gson().fromJson(
                            result.errorBody()?.string(),
                            ErrorResponse::class.java
                        )
                        currentWeatherStateFlow.value =
                            Resource.error(responseError.error?.message.toString())
                    }
                } catch (throwable: Throwable) {
                    throwable.localizedMessage?.let {
                        println("Error $it")
                        currentWeatherStateFlow.value = Resource.error(it)
                    }
                }
            }
        }
    }

    fun fetchForecastWeather(cityName: String, day:Int) {
        currentWeatherStateFlow.value = Resource.loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = mainRepository.repoGetForecastWeather(cityName,day)
                    if (result.isSuccessful) {
                        currentWeatherStateFlow.value = Resource.success(result.body())
                    } else {
                        val responseError = Gson().fromJson(
                            result.errorBody()?.string(),
                            ErrorResponse::class.java
                        )
                        currentWeatherStateFlow.value =
                            Resource.error(responseError.error?.message.toString())
                    }
                } catch (throwable: Throwable) {
                    throwable.localizedMessage?.let {
                        println("Error $it")
                        currentWeatherStateFlow.value = Resource.error(it)
                    }
                }
            }
        }
    }

    fun updateCityName(text: String) {
        cityName.value = text
    }

}