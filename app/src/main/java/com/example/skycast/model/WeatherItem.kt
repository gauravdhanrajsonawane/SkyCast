package com.example.skycast.model

data class WeatherItem(
    val clouds: Clouds,
    val dt: Int,
    val dttxt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherObject>,
    val wind: Wind
)