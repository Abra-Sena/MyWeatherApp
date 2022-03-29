package com.emissa.apps.myweatherapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emissa.apps.myweatherapp.convertFromKelvinToFahrenheit
import com.emissa.apps.myweatherapp.databinding.FragmentForecastDetailsBinding
import com.emissa.apps.myweatherapp.roundValueToFloor


class ForecastDetailsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentForecastDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val forecast = weatherViewModel.getForecastDetails()
        val cityName = weatherViewModel.getCity()
        val tempPredict = forecast?.main?.temp?.let {
            roundValueToFloor(convertFromKelvinToFahrenheit(it))
        }
        val feltTemp = forecast?.main?.feelsLike?.let {
            roundValueToFloor(convertFromKelvinToFahrenheit(it))
        }
        val humidity = forecast?.main?.humidity.toString()
        val description = forecast?.weather?.get(0)?.description

        binding.apply {
            city.text = cityName
            currentDate.text = forecast?.dtTxt
            weatherTempPredict.text = tempPredict
            weatherTempFelt.text = "Feels like: $feltTemp"
            weatherHumidity.text = "Humidity: ${humidity}%"
            weatherDescription.text = "Note: $description"
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}