package com.example.weather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
@Composable
fun WeatherAppbar(title:String="London",
                  icon:ImageVector?=null,
                  isMainScreen:Boolean=true,
                  elevation:Dp=0.dp,
                  navController: NavController,
                  onAddActionClicked:() -> Unit ={},
                  onButtonClicked:() -> Unit ={}){


     CenterAlignedTopAppBar( colors = TopAppBarDefaults.topAppBarColors(
         containerColor = MaterialTheme.colorScheme.primary,
         titleContentColor = Color.White),
         title = {
         Text(text =title,
             color = MaterialTheme.colorScheme.inversePrimary,
             style = TextStyle(fontWeight = FontWeight.Bold,
                 fontSize = 23.sp)
         )},
         actions = {
             //actions are always at end of the Screen
             if(isMainScreen){
                 IconButton(onClick = {
                     onAddActionClicked.invoke()
                 }) {
                     Icon(imageVector = Icons.Default.Search ,
                         contentDescription = "Search Icon",
                         tint = MaterialTheme.colorScheme.inversePrimary)
                 }

                 IconButton(onClick = {

                 }) {
                     Icon(imageVector = Icons.Rounded.MoreVert,
                         contentDescription = "more item",
                         tint = MaterialTheme.colorScheme.inversePrimary)
                 }

             }
             else Box{}
         },
         navigationIcon = {
             if(icon!=null){
                 Icon(imageVector = icon,
                     contentDescription = "arrow back",
                     //tint = MaterialTheme.colorScheme.onSecondary,
                     modifier = Modifier.padding(10.dp)
                         .clickable {
                         onButtonClicked.invoke()
                     })
             }
         }
     )
}