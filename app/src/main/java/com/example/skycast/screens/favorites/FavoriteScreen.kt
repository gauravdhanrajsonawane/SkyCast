package com.example.skycast.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.skycast.model.Favorite
import com.example.skycast.navigation.WeatherScreens
import com.example.skycast.widgets.WeatherAppBar

@Composable
fun FavoriteScreen(navController: NavController,
                   favoriteViewModel: FavoriteViewModel = hiltViewModel()) {

    Scaffold(topBar = { WeatherAppBar(
        title = "Favorite Cities",
        icon = Icons.Default.Close,
        isMainScreen = false,
        navController = navController){
        navController.popBackStack()
    }
    })
    { innerPadding ->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()) {

            val list = favoriteViewModel.favList.collectAsState().value

            LazyColumn {
                items(items = list) {

                    CityRow(it, navController = navController, favoriteViewModel)
                }

            }



        }


    }

}

@Composable
fun CityRow(it: Favorite,
            navController: NavController,
            favoriteViewModel: FavoriteViewModel
) {

    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth()
        .height(50.dp)
        .clickable {
            navController.navigate(WeatherScreens.MainScreen.name + "/${it.city}")
        },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xFF9FE0DB)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {

            Text(text = it.city, modifier = Modifier.padding(start = 4.dp))

            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(text = it.country,
                    modifier = Modifier.padding(4.dp))

            }

            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "delete",
                modifier = Modifier.clickable {

                    favoriteViewModel.deleteFavorite(it)

                },
                tint = Color.Red.copy(alpha = 0.3f)
            )

        }

    }



}
