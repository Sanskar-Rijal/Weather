package com.example.weather.screens.SettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.widgets.WeatherAppbar

@Composable
fun SettingsScreen(navController: NavController,
                   settingsViewmodel:SettingsViewModel= hiltViewModel()
){

    //creating state for button
    var unitToggleState by remember {
        mutableStateOf(false)
    }

    val measurementUnits = listOf("Imperial (F)","Metric (C)") //it will toggle between F , C so i have to add another state

    var choiceState by remember {
        mutableStateOf("")
    }

   Scaffold(topBar = {
       WeatherAppbar(title = "Settings",
           navController =navController,
           icon = Icons.AutoMirrored.Filled.ArrowBack,
           isMainScreen = false,
           ){
           navController.popBackStack()
       }
   }){ contentpadding->
       Surface  (modifier = Modifier
           .padding(contentpadding)
           .fillMaxWidth()
           .fillMaxHeight()) {
           Column(verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally) {
               Text(text = "Change Units Of Measurement",
                   modifier = Modifier.padding(bottom = 13.dp))

               IconToggleButton(checked = !unitToggleState,
                   onCheckedChange = { bool->//when button is clicked
                       unitToggleState=!bool
                        if (unitToggleState){
                            choiceState= "Imperial (F)"
                        }
                       else{
                            choiceState="Metric (C)"
                        }
                   },
                   modifier = Modifier.fillMaxWidth(0.5f)
                       .clip(shape = RectangleShape)
                       .padding(5.dp)
                       .background(MaterialTheme.colorScheme.inversePrimary)
               ) {
                   Text(text = if(unitToggleState)"Fahrenheit F" else "Celcius C", color = Color.White )
               }

           }
       }
   }
}