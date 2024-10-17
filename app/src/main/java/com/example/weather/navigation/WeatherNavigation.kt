package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.screens.main.MainScreen
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.splash.WeatherSplashScreen

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
           val mainViewModel:MainViewModel= hiltViewModel()
           MainScreen(navController,mainViewModel)
       }
   }
}