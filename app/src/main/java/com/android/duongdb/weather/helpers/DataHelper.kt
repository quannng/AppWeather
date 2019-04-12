package com.android.duongdb.weather.helpers

import android.annotation.SuppressLint
import android.util.Log

import com.android.duongdb.weather.models.ForecastWeather
import com.android.duongdb.weather.models.Weather
import com.google.gson.Gson

import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

object DataHelper {
    @Throws(JSONException::class)
    fun renderWeather(data: String): Weather? {
        val gson = Gson()
        val weatherRetrofit = gson.fromJson(data, com.android.duongdb.weather.WeatherRetrofit::class.java)
        val json = JSONObject(data)
        val weather = Weather()
        try {
            weather.cityName = weatherRetrofit.location?.city
            weather.countryName = weatherRetrofit.location?.country
            weather.updateTime = convertLongToDate(weatherRetrofit.currentObservation?.pubDate!!)
            weather.temp = ((weatherRetrofit.currentObservation.condition?.temperature!! - 32) / 1.8).toInt()
            val condition = weatherRetrofit.currentObservation.condition
            weather.condCode = condition.code!!
            weather.condText = condition.text
            weather.windSpeed = weatherRetrofit.currentObservation.wind?.speed!!.toInt()
            weather.windDirection = mapWindName(weatherRetrofit.currentObservation.wind.direction!!)
            val atmosphere = weatherRetrofit.currentObservation.atmosphere!!
            weather.humidity = atmosphere.humidity!!
            weather.pressure = atmosphere.pressure
            weather.visibility = atmosphere.visibility?.toDouble()
            val astronomy = weatherRetrofit.currentObservation.astronomy
            weather.sunrise = astronomy!!.sunrise
            weather.sunset = astronomy.sunset

            val forecastArray = weatherRetrofit.forecasts
            val forecastWeathers = ArrayList<ForecastWeather>()
            for (i in 0 until forecastArray.size) {
                val forecastWeather = ForecastWeather()
                val forecastObject = forecastArray[i]
                forecastWeather.day = forecastObject.day
                forecastWeather.low = ((forecastObject.low!! - 32) / 1.8).toInt()
                forecastWeather.high = ((forecastObject.high!! - 32) / 1.8).toInt()
                forecastWeather.code = forecastObject.code!!
                forecastWeathers.add(forecastWeather)
            }
            weather.forecastWeathers = forecastWeathers
        } catch (e: Exception) {
            Log.e("WeatherApp", "Data not found")
            return null
        }

        return weather
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertLongToDate(value: Int): String {
        val date = Date(value.toLong())
        val df2 = SimpleDateFormat("HH:mm a", Locale.getDefault())
        df2.timeZone = TimeZone.getTimeZone("UTC")
        return df2.format(date)
    }


    private fun mapWindName(direction: Int): String {
        return if (direction > 348.75 || direction <= 11.25)
            "N"
        else if (direction <= 33.75)
            "NNE"
        else if (direction <= 56.25)
            "NE"
        else if (direction <= 78.75)
            "ENE"
        else if (direction <= 101.25)
            "E"
        else if (direction <= 123.75)
            "ESE"
        else if (direction <= 146.25)
            "SE"
        else if (direction <= 168.75)
            "SSE"
        else if (direction <= 191.25)
            "S"
        else if (direction <= 213.75)
            "SSW"
        else if (direction <= 236.25)
            "SW"
        else if (direction <= 258.75)
            "WSW"
        else if (direction <= 281.25)
            "W"
        else if (direction <= 303.75)
            "WNW"
        else if (direction <= 326.25)
            "NW"
        else
            "NNW"
    }


}
