package com.emissa.apps.myweatherapp.rest

import com.emissa.apps.myweatherapp.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET(FORECAST_PATH)
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appId") apiKey: String = API_KEY
    ): Response<Results>

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
        private const val FORECAST_PATH = "data/2.5/forecast"
        private const val API_KEY = "65d00499677e59496ca2f318eb68c049"
    }
}