package com.emissa.apps.myweatherapp.rest

import com.emissa.apps.myweatherapp.model.Results
import retrofit2.Response

class WeatherRepositoryImpl(
    private val weatherServiceApi: ServiceApi
) : WeatherRepository {

    override suspend fun getCityForecast(city: String): Response<Results> {
        return weatherServiceApi.getForecast(city)
    }
}

interface WeatherRepository {
    suspend fun getCityForecast(city: String) : Response<Results>
}