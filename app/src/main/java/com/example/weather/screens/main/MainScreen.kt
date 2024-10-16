package com.example.weather.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel= hiltViewModel()){
    Text(text = "welcome to main screen")
}