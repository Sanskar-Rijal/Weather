package com.example.weather.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.weather.R
import com.example.weather.data.DataorException
import com.example.weather.model.City
import com.example.weather.model.Weather
import com.example.weather.model.WeatherItem
import com.example.weather.navigation.WeatherScreens
import com.example.weather.screens.SettingsScreen.SettingsViewModel
import com.example.weather.utils.day
import com.example.weather.utils.formatDate
import com.example.weather.utils.formatDecimal
import com.example.weather.utils.formatTimeandDate
import com.example.weather.widgets.WeatherAppbar

@Composable
fun MainScreen(navController: NavController,
               viewModel: MainViewModel= hiltViewModel(),
               settingsViewmodel:SettingsViewModel= hiltViewModel(),
               city:String?){

    val unitfromdb =settingsViewmodel.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("imperial")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }

    var CheckTemperature:Boolean?=null

    if(!unitfromdb.isNullOrEmpty()){

        unit =unitfromdb[0].unit.split(" ")[0].lowercase()//it is saved as IMPERIAL "F"
        //split " " split it's from space and [0] picks the first letter i.e imperial
        isImperial= unit =="imperial"  //will return true or false
        val weatherData = produceState<DataorException<Weather,Boolean,Exception>>(
            initialValue =DataorException(loading = true)){
            value = viewModel.getWeatherData(city = city.toString())
        }.value

        if(weatherData.loading==true){
            CircularProgressIndicator()
        }
        else if (weatherData.data !=null ){

            var currentweather: Double =
                (formatDecimal(weatherData.data!!.list[0].main.temp)).toDouble()

           // var nextweather:Double = (formatDecimal(weatherData.data!!.list[0].main.temp))
            if(isImperial) {
                //means F
                weatherData.data!!.list.forEachIndexed { index, it ->

                    currentweather =it.main.temp -273.15

                    farnheitConverter(currentweather) { data ->
                        currentweather = (formatDecimal((data * 1.8) + 32)).toDouble()
                    }
                   weatherData.data!!.list[index].main.temp=currentweather
                    CheckTemperature = false
                }
            }
            else{
                //means degree C
                weatherData.data!!.list.forEachIndexed { index, it ->
                    currentweather =it.main.temp - 273.15
                    weatherData.data!!.list[index].main.temp=currentweather
                CheckTemperature=true
                    }
            }
            MainScaffold(weather=weatherData.data!!,navController,CheckTemperature!!)
        }
    }
    }


//top bar for main screen
@Composable
fun MainScaffold(weather: Weather,navController: NavController,CheckTemp:Boolean){

    Scaffold(
        topBar = {
            WeatherAppbar(title = weather.city.name + " , ${weather.city.country}",
                navController = navController,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                }){
                //onbuttonclicked is last parameter so we can use trailing lamda
            }
        }
    ) {
        MainContent(Modifier.padding(it),weather,CheckTemp)
    }

}


@Composable
fun MainContent(modifier: Modifier,data:Weather,CheckTemp: Boolean){

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

                 Text(text = formatDecimal(data.list[0].main.temp) + "°",
                     style = MaterialTheme.typography.displayLarge,
                     fontWeight = FontWeight.ExtraBold)

                 Text(text = data.list[0].weather[0].description,
                     fontStyle = FontStyle.Italic)
             }
         }
         HumiditywindAndPressure(weather = data.list[0])
         HorizontalDivider(thickness = 2.dp,
             color = MaterialTheme.colorScheme.primary)
         SunRiseandSunset(weather = data.city)

         Text(text = "This Week",
             fontWeight = FontWeight.ExtraBold,
             fontSize = 17.sp)

         //scrollable weather for this week
         WeatherRow(weather = data.list,CheckTemp)
     }
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

//composable for sunrise and sunset
@Composable
fun SunRiseandSunset(weather: City)
{
    Row(modifier = Modifier.padding(10.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Row(Modifier.padding(4.dp)) {

            Icon(painter = painterResource(R.drawable.sunrise),
                contentDescription = "sunRise",
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp))
            Text(text= formatTimeandDate( weather.sunrise),
                modifier =Modifier.padding(2.dp),
                style = MaterialTheme.typography.bodyMedium)

        }


        Row(modifier = Modifier.padding(4.dp)){

            Icon(painter = painterResource(R.drawable.sunset),
                contentDescription = "Sunset",
                modifier = Modifier
                    .padding(2.dp)
                    .size(20.dp))
            Text(text= formatTimeandDate(weather.sunset),
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.bodyMedium)

        }
    }
}

//for showing list of weather for this week
@Composable
fun WeatherRow(weather: List<WeatherItem>,CheckTemp: Boolean){
    Surface(modifier = Modifier
        .padding(10.dp)
        .fillMaxHeight()
        .fillMaxWidth(),
        color = Color.LightGray,
        shape = RoundedCornerShape(17.dp)
    ) {
        LazyColumn {
            items(items = weather){ item: WeatherItem ->
                    weatherDetailedRow(item,CheckTemp)
                }
            }
        }
    }
//items of row inside Weatherrow
@Composable
fun weatherDetailedRow(data: WeatherItem,CheckTemp: Boolean){
    Card(modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)) {
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            //day  icon description temperature

            Text(text = day(data.dt),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold)

            WeatherStateImage("https://openweathermap.org/img/wn/${data.weather[0].icon}.png",
                40)

            Text(text = data.weather[0].description,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold)

            //if true then we are in C, else we are in F
            Text(text = if(CheckTemp)formatDecimal(data.main.temp)+"°" else //this is C
                formatDecimal(data.main.temp)+"°",//this is F
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold)
        }
    }
}

//composable for showing current weather with image
@Composable
fun WeatherStateImage(imageUrl:String,size:Int=80){
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
        modifier = Modifier.size(size.dp).clip(CircleShape)
    )
}


fun farnheitConverter(data:Double,returndata: (Double) ->Unit={} ){
    returndata(data)
}