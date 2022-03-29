package com.emissa.apps.myweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emissa.apps.myweatherapp.R
import com.emissa.apps.myweatherapp.convertFromKelvinToFahrenheit
import com.emissa.apps.myweatherapp.databinding.ForecastItemBinding
import com.emissa.apps.myweatherapp.model.Forecast
import com.emissa.apps.myweatherapp.model.Main
import com.emissa.apps.myweatherapp.roundValueToFloor
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Adapter class to adapt data to the view
 */
class WeatherAdapter(
    private val itemClickListener: ItemClicked,
    private val forecastList: MutableList<Forecast> = mutableListOf()
) : RecyclerView.Adapter<ForecastViewHolder>() {

    fun setForecast(newForecast: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(newForecast)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = ForecastItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForecastViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.bind(forecast)
    }

    override fun getItemCount(): Int = forecastList.size
}

class ForecastViewHolder(
    private val binding: ForecastItemBinding,
    private val forecastClicked: ItemClicked
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {
        // convert normal temp from kelvin to fahrenheit
        val temp = convertFromKelvinToFahrenheit(forecast.main.temp)
        // round converted temperature to floor
        val roundedTemp = roundValueToFloor(temp)
        // convert felt temp from kelvin to fahrenheit
        val feltedTemp = convertFromKelvinToFahrenheit(forecast.main.feelsLike)
        // round felt temp to floor
        val feltRounded = roundValueToFloor(feltedTemp)
        val tempDisplay = "$roundedTemp / $feltRounded"

        binding.currentDate.text = forecast.dtTxt
        binding.weatherTemp.text = tempDisplay

        binding.forecastItem.setOnClickListener {
            forecastClicked.onItemClicked(forecast)
        }
    }
}