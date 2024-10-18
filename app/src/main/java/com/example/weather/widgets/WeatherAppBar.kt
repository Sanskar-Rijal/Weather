package com.example.weather.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WeatherAppbar(title:String="London",
                  icon:ImageVector?=null,
                  isMainScreen:Boolean=true,
                  elevation:Dp=0.dp,
                  //navController: NavController,
                  onAddActionClicked:() -> Unit ={},
                  onButtonClicked:() -> Unit ={}){
     TopAppBar( colors = TopAppBarDefaults.topAppBarColors(
         containerColor = MaterialTheme.colorScheme.inversePrimary,
         titleContentColor = Color.White
     ),
         title = {
         Text(text =title,
             color = MaterialTheme.colorScheme.primary,
             style = TextStyle(fontWeight = FontWeight.Bold,
                 fontSize = 17.sp)
         )
     })
}