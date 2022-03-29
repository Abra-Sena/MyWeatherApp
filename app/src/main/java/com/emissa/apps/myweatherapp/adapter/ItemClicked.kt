package com.emissa.apps.myweatherapp.adapter

import com.emissa.apps.myweatherapp.model.Forecast

interface ItemClicked {
    fun onItemClicked(forecast: Forecast)
}