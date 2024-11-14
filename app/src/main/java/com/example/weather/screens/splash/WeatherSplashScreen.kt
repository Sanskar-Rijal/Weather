package com.example.weather.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.R
import com.example.weather.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController:NavController){
    //animating  the splash screeen
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    //creating default city
    val defaultcity="Kathmandu"

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800 ,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it) })
        )
        delay(1000L)
        navController.navigate(WeatherScreens.MainScreen.name+"/$defaultcity")
    }

    Surface(
        modifier = Modifier
        .padding(17.dp)
        .size(330.dp)
        .scale(scale.value),
        shape = CircleShape,
        border = BorderStroke(3.dp,Color.LightGray)) {
        Column(modifier = Modifier
            .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Image(
                modifier = Modifier.size(95.dp)
                    .padding(10.dp),
                painter = painterResource(R.drawable.splash),
                contentDescription = "splash Screen",
                contentScale = ContentScale.Fit)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text="Let's Check Weather",
                style = MaterialTheme.typography.titleMedium,
                color = Color.LightGray)
        }

    }
}