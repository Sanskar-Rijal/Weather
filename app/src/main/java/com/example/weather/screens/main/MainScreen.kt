package com.example.weather.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.DataorException
import com.example.weather.model.Main
import com.example.weather.model.Weather
import com.example.weather.widgets.WeatherAppbar

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel= hiltViewModel()){

    val weatherData = produceState<DataorException<Weather,Boolean,Exception>>(
        initialValue =DataorException(loading = true)){
        value = viewModel.getWeatherData("London")
    }.value

    if(weatherData.loading==true){
        CircularProgressIndicator()
    }
    else if (weatherData.data !=null ){
        MainScaffold(weather=weatherData.data!!,navController)
    }
}

//testing
@Composable
fun MainScaffold(weather: Weather,navController: NavController){

    Scaffold(
        topBar = {
            WeatherAppbar()
        }
    ) {
        Text(modifier = Modifier.padding(it), text = " ")

        MainContent(data=weather)

    }

}

@Composable
fun MainContent(data:Weather){
     Text(text =data.city.name)
}
