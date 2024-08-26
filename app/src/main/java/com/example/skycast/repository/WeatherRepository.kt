package com.example.skycast.repository

import com.example.skycast.data.DataOrException
import com.example.skycast.model.Weather
import com.example.skycast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi){

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {

        val response = try {

            api.getWeather(query = cityQuery)

        }catch (e:Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }
}