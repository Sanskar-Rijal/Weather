package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather.screens.AboutScreen.AboutScreen
import com.example.weather.screens.FavScreen.FavoriteScreen
import com.example.weather.screens.SettingsScreen.SettingsScreen
import com.example.weather.screens.main.MainScreen
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.search.SearchScreen
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


       //www.google.com/cityname="london" we are passing the city variable like the url in web
       val route=WeatherScreens.MainScreen.name
       composable("$route/{city}",//it simply means
           // composable(WeatherScreens.MainScreen.name+"/{city}"))
           arguments = listOf(navArgument(name = "city"){
               type= NavType.StringType //we are passing string type i.e name of city
           })) { BackStackEntry->

           BackStackEntry.arguments?.getString("city").let { cityname->

               val mainViewModel:MainViewModel= hiltViewModel()
               MainScreen(navController,mainViewModel,city= cityname)

           } //all the names must be same i.e city

       }

       composable(WeatherScreens.SearchScreen.name){
           SearchScreen(navController)
       }

       composable(WeatherScreens.SettingsScreen.name){
           SettingsScreen(navController)
       }
       composable(WeatherScreens.FavoriteScreen.name){
           FavoriteScreen(navController)
       }
       composable(WeatherScreens.AboutScreen.name){
           AboutScreen(navController)
       }

   }
}