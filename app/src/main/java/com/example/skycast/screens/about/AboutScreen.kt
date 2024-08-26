package com.example.skycast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.skycast.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            icon = Icons.Default.Close,
            isMainScreen = false,
            navController = navController) {
            navController.popBackStack()
        }
    }) { innerPadding ->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .fillMaxHeight()) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

//                Text(text = stringResource(id = R.string.about_app),
//                    fontWeight = FontWeight.Bold)
//
//                Text(text = stringResource(id = R.string.api_used),
//                    fontWeight = FontWeight.Light)

                Text(text = "Radha", fontWeight = FontWeight.Bold)

            }

        }

    }
}