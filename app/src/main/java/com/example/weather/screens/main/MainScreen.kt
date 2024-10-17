package com.example.weather.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.DataorException
import com.example.weather.model.Weather

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel= hiltViewModel()){
    ShowData(viewModel)
}

//testing
@Composable fun ShowData(viewModel: MainViewModel){

    val weatherData = produceState<DataorException<Weather,Boolean,Exception>>(
        initialValue =DataorException(loading = true)){
        value = viewModel.getWeatherData("London")
    }.value

    if(weatherData.loading==true){
        CircularProgressIndicator()
    }
    else if (weatherData.data !=null ){
        Text(text = "MainScreen ${weatherData.data!!.toString()}", color = Color.Red)
    }
}