package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.Screens.MainScreen
import com.example.weather.Screens.WeatherSplashScreen

//is the navcontroller , has the ability to control any screen in the app
@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()

   NavHost(
       navController = navController,
       startDestination = WeatherScreens.SplashScreen.name){
       //creating navgraphs
       composable(WeatherScreens.SplashScreen.name){
           WeatherSplashScreen(navController)
       }

       composable(WeatherScreens.MainScreen.name){
           MainScreen(navController)
       }
   }
}