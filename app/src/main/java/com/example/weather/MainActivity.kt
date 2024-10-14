package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.navigation.WeatherNavigation

import com.example.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint //so that hilt knows mainactivity will be getting access to all of those
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myapp {
                WeatherApp()
            }
        }
    }
}


@Composable
fun myapp(content:@Composable () -> Unit){
    WeatherTheme {
        content()
    }
}

@Preview
@Composable
fun WeatherApp (){
    Surface(color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()//fillmaxSize means fill max width and height
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            WeatherNavigation()
        }
    }
}

