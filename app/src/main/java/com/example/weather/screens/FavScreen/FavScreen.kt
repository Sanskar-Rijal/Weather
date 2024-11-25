package com.example.weather.screens.FavScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.weather.model.Favorites
import com.example.weather.navigation.WeatherScreens
import com.example.weather.screens.main.MainScreen
import com.example.weather.widgets.WeatherAppbar


@Composable
fun FavoriteScreen(
    navController: NavController,
    favViewmodel: FavViewmodel = hiltViewModel()
){
    Scaffold(topBar = {WeatherAppbar(
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        navController = navController,
        isMainScreen = false,
        title ="Favorite Cities"){
        navController.popBackStack()
    }}) {  pad->
        Surface(modifier = Modifier
            .padding(pad)
            .padding(5.dp)
            .fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                val list = favViewmodel.favlist.collectAsState().value
                LazyColumn {
                    items(items =list){
                       CityRow(it,navController,favViewmodel)
                    }
                }

            }

        }

    }
}



@Composable
fun CityRow(favorite: Favorites,
            navController: NavController,
            favViewmodel: FavViewmodel){
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name+"/${favorite.city}")
            },
        shape = CircleShape.copy(
            topEnd = CornerSize(6.dp),
            bottomStart = CornerSize(6.dp)
        ),
        color = Color(0xFF669bbc)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text =favorite.city, modifier = Modifier.padding(start = 4.dp))

            //creating surface for showing country code example NP
            Surface(modifier = Modifier
                .padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFf4acb7)
            ) {
                Text(text = favorite.country,
                    modifier = Modifier.padding(6.dp),
                    style = MaterialTheme.typography.titleSmall)
            }

            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Delete Button",
                tint = Color.Red.copy(alpha = 0.3f),
                modifier = Modifier.clickable {
                    favViewmodel.deletefav(favorite)
                })

        }
    }
}
