package com.emissa.apps.myweatherapp.viewmodel

import com.emissa.apps.myweatherapp.model.Results

sealed class ResultState {
    object LOADING : ResultState()
    class SUCCESS(val results: Results) : ResultState()
    class ERROR(val error: Throwable) : ResultState()
}
