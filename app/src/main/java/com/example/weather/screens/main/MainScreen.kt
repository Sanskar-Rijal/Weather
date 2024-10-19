package com.example.weather.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.weather.R
import com.example.weather.data.DataorException
import com.example.weather.model.Main
import com.example.weather.model.Weather
import com.example.weather.model.WeatherItem
import com.example.weather.utils.formatDate
import com.example.weather.utils.formatDecimal
import com.example.weather.widgets.WeatherAppbar

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel= hiltViewModel()){

    val weatherData = produceState<DataorException<Weather,Boolean,Exception>>(
        initialValue =DataorException(loading = true)){
        value = viewModel.getWeatherData("Kathmandu")
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
            WeatherAppbar(title = weather.city.name + " , ${weather.city.country}"){
                //onbuttonclicked is last parameter so we can use trailing lamda
            }
        }
    ) {
        MainContent(Modifier.padding(it),weather)

    }

}


@Composable
fun MainContent(modifier: Modifier,data:Weather){

    //for image
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"

     Column(modifier = modifier.padding(4.dp)
         .fillMaxWidth(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally) {
         Text(text = formatDate(data.list[0].dt),
             color = Color.White,
             style = MaterialTheme.typography.titleMedium,
             fontWeight = FontWeight.SemiBold,
             modifier = Modifier.padding(6.dp))

         Surface(modifier = Modifier
             .padding(4.dp)
             .size(200.dp),
             shape = CircleShape,
             color = MaterialTheme.colorScheme.primary) {
             Column(verticalArrangement = Arrangement.Center,
                 horizontalAlignment = Alignment.CenterHorizontally) {

                 //adding image
                 WeatherStateImage(imageUrl = imageUrl)

                 Text(text = formatDecimal(data.list[0].main.temp-273.15) + "°",
                     style = MaterialTheme.typography.displayLarge,
                     fontWeight = FontWeight.ExtraBold)
                 Text(text = data.list[0].weather[0].description,
                     fontStyle = FontStyle.Italic)
             }
         }
         HumiditywindAndPressure(weather = data.list[0])
     }
}



//composable for showing current weather with image
@Composable
fun WeatherStateImage(imageUrl:String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
//            .listener(
//                onError = { request: ImageRequest, error: ErrorResult ->
//                    Log.e("AsyncImage", "Image loading error: ${error.throwable}")
//                }
//            )
            .build(),
        contentDescription = "image for Weather",
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(R.drawable.dummy),
        error = painterResource(R.drawable.error),
        modifier = Modifier.size(80.dp).clip(CircleShape)
    )
}


//humidity wind and pressure

@Composable
fun HumiditywindAndPressure(weather:WeatherItem){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(R.drawable.humidity),
                contentDescription = "Humidity icon",
                modifier = Modifier.size(20.dp))
            Text(text = " ${weather.main.humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(R.drawable.pressure),
                contentDescription = "Pressure icon",
                modifier = Modifier.size(20.dp))
            Text(text = " ${weather.main.pressure} hPa",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(R.drawable.wind),
                contentDescription = "Humidity icon",
                modifier = Modifier.size(20.dp))
            Text(text = " ${weather.wind.speed} m/s",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}