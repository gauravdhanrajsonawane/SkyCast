package com.example.skycast.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.skycast.data.DataOrException
import com.example.skycast.model.Weather
import com.example.skycast.model.WeatherItem
import com.example.skycast.navigation.WeatherScreens
import com.example.skycast.screens.settings.SettingsViewModel
import com.example.skycast.utils.formatDate
import com.example.skycast.utils.formatDecimals
import com.example.skycast.widgets.HumidityWindPressureRow
import com.example.skycast.widgets.SunriseSunsetRow
import com.example.skycast.widgets.WeatherAppBar
import com.example.skycast.widgets.WeatherDetailRow
import com.example.skycast.widgets.WeatherStateImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
//    //val curCity: String = if(city!!.isBlank()) "Seattle" else city
//    val unitFromDb = settingsViewModel.unitList.collectAsState().value
//
//    var unit by remember {
//        mutableStateOf("imperial")
//    }
//    var isImperial by remember {
//        mutableStateOf(false)
//    }

    //if (!unitFromDb.isNullOrEmpty()) {
//        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
//        isImperial = unit == "imperial"

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {

        value = mainViewModel.getWeatherData(city = city.toString())

    }.value


    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {

        MainScaffold(weather = weatherData.data!!, navController = navController)

    }
}


@Composable          // mainViewModel: MainViewModel this is errased why ?
fun MainScaffold(weather: Weather, navController: NavController) {

    Scaffold(topBar = {

        WeatherAppBar(
            title = weather.city.name + " ,${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },

            elevation = 5.dp
        )

    }) { innerPadding ->
        MainContent(
            data = weather,
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun MainContent(data: Weather, modifier: Modifier) {
//    Text(text = data.city.name,
//        modifier = modifier)

    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"

    Column(
        modifier = modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = formatDate(data.list[0].dt),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeatherStateImage(imageUrl = imageUrl)

//                var y = (data.list[0].main.temp)
//                var x = ( y - 32) * (5/9)

                Text(
                    text = formatDecimals(data.list[0].main.temp) + "°F",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 45.sp
                )

                Text(
                    text = data.list[0].weather[0].main,
                    fontStyle = FontStyle.Italic
                )

            }

        }

        HumidityWindPressureRow(weather = data.list[0])

        HorizontalDivider()

        SunriseSunsetRow(weather = data)

        Text(
            text = "This Week",
            fontWeight = FontWeight.Bold
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { it: WeatherItem ->

                    WeatherDetailRow(weather = it)

                }

            }

        }

    }

}
