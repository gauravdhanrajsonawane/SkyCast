package com.example.skycast.network

import com.example.skycast.model.Weather
import com.example.skycast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("APPID") AAPID: String = Constants.API_KEY
    ): Weather
}//this is test