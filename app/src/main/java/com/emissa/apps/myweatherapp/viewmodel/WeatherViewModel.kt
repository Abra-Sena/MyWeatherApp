package com.emissa.apps.myweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emissa.apps.myweatherapp.model.Forecast
import com.emissa.apps.myweatherapp.model.Results
import com.emissa.apps.myweatherapp.rest.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    // this will be changing the value of the mutable live data
    private val _cityForecast: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val cityForecast: LiveData<ResultState> get() = _cityForecast

    private val _cityName: MutableLiveData<String> = MutableLiveData()
    private val cityName: LiveData<String> get() = _cityName

    private val _mForecast: MutableLiveData<Forecast> = MutableLiveData()
    private val forecast: LiveData<Forecast> get() = _mForecast

    // get a forecast details on click
    fun getForecastDetails(): Forecast? = forecast.value

    fun setForecast(forecast: Forecast) {
        _mForecast.value = forecast
    }
    /**
     * Set the searched city name
     */
    fun setCityName(city: String) {
        _cityName.value = city
    }
    fun getCity(): String = cityName.value ?: ""


    fun getForecast(city: String) {
        viewModelScope.launch(ioDispatcher) {
            // worker thread
            try {
                val response = weatherRepository.getCityForecast(city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.Main) {
                            // inside main thread
                            _cityForecast.value = ResultState.SUCCESS(it)
                        }
                    } ?: throw Exception("Response is null")
                } else  { // handle error
                    throw Exception("No success on search!")
                }
            } catch (e: Exception) {
                _cityForecast.postValue(ResultState.ERROR(e))
            }
        }
    }
}