package com.example.weather.screens.AboutScreen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.weather.R
import com.example.weather.widgets.WeatherAppbar

@Composable
fun AboutScreen(navController: NavController){
    Scaffold(topBar = {
        WeatherAppbar(navController = navController,
            title = "About",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            isMainScreen = false){
            navController.popBackStack()
        }}) {innerpadding->
        Surface(modifier = Modifier
            .padding(innerpadding)
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text= stringResource(id = R.string.about_app),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                    )
                Text(text= stringResource(id = R.string.weather_info),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}