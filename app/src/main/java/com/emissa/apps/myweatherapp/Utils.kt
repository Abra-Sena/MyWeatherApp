package com.emissa.apps.myweatherapp

import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


fun Int.formatDateAndTime() : String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()
        val dateFormat = SimpleDateFormat("E")
        val timeFormat = SimpleDateFormat("h:mm a")
        return dateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun convertFromKelvinToFahrenheit(kelvinTemp: Double) : Double {
    return ((kelvinTemp - 273.15) * 1.8) + 32
}

fun convertFromKelvinToCelsius(kelvinTemp: Double) : Double {
    return (kelvinTemp - 273.15)
}

fun roundValueToFloor(value: Double) : String {
    val desiredFormat = DecimalFormat("#.#")
    desiredFormat.roundingMode = RoundingMode.HALF_DOWN

    return desiredFormat.format(value)
}