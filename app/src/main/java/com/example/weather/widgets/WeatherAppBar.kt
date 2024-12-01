package com.example.weather.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.model.Favorites
import com.example.weather.navigation.WeatherScreens
import com.example.weather.screens.FavScreen.FavViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppbar(title:String="London",
                  icon:ImageVector?=null,
                  isMainScreen:Boolean=true,
                  elevation:Dp=0.dp,
                  FavViewModel:FavViewmodel= hiltViewModel(),
                  navController: NavController,
                  onAddActionClicked:() -> Unit ={},
                  onButtonClicked:() -> Unit ={}){

    //creating state for 3 dots to show favorite, setting and etc
    val showDialog = remember{
        mutableStateOf(false) //when we click on the icon then we want it to be true
    }

    if(showDialog.value){
        ShowSettingDropDownMenu(ShowDialouge=showDialog,navController=navController)
    }

    //creating mutable state for toast
     val showToast = remember {
         mutableStateOf(false)
     }

    //to get a context for toast
    val context = LocalContext.current


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
                     onAddActionClicked.invoke() //it will take to search screen
                 }) {
                     Icon(imageVector = Icons.Default.Search ,
                         contentDescription = "Search Icon",
                         tint = MaterialTheme.colorScheme.inversePrimary)
                 }

                 IconButton(onClick = {
                     showDialog.value= true
                 }) {
                     Icon(imageVector = Icons.Rounded.MoreVert,
                         contentDescription = "more item",// 3dot icon
                         tint = MaterialTheme.colorScheme.inversePrimary)
                 }

             }
             else Box{}
         },
         navigationIcon = {
             if(icon!=null) {
                 Icon(imageVector = icon,
                     contentDescription = "arrow back",
                     tint = MaterialTheme.colorScheme.inversePrimary,
                     modifier = Modifier
                         .padding(10.dp)
                         .clickable {
                             onButtonClicked.invoke()
                         })
             }
                 //if its main screen i want to show the icon to mark favorites check
                 if (isMainScreen){

                     //creating a variable to check data base , whether current city exists or not
                     val isAlreadyExist = FavViewModel.favlist.collectAsState().value.filter {item ->
                         (item.city==title.split(",")[0])
                         //isAlreadyExist is an array i.e list
                     }

                     if(isAlreadyExist.isNullOrEmpty() ) {
                         Icon(
                             Icons.Default.Favorite,
                             contentDescription = "Favorite icon",
                             modifier = Modifier
                                 .scale(0.9f)
                                 .padding(start = 9.dp)
                                 .clickable {
                                     FavViewModel.insertfav(
                                         Favorites(
                                             city = title.split(",")[0], //city name
                                             country = title.split(",")[1] //country code
                                         )).run {
                                             showToast.value=true
                                     }
                                     //Biratnagar, Np ,it will take first item from the comma i.e Biratnagar
                                     /**
                                      * or we can do this
                                      * val dataList = title.split(",")
                                      * city=dataList[0]
                                      * country=dataList[1]
                                      */
                                 },
                             tint = Color.Red.copy(alpha = 0.6f)
                         )
                     }
                     else {
                         //if we are on fav screen we don't want to see fav icon
                         showToast.value=false
                         Box {  }
                     }
                     ShowToast(context = context,showIt= showToast )
                 }
         }
     )
}

@Composable
fun ShowToast(context:Context , showIt: MutableState<Boolean>) {
    if(showIt.value){
        Toast.makeText(context, "City Added To Favorites", Toast.LENGTH_SHORT).show()
    }
}

//for show dialouge

@Composable
fun ShowSettingDropDownMenu(ShowDialouge: MutableState<Boolean>, navController: NavController) {

    var expand by remember {
        mutableStateOf(true)
    }

    val items = listOf("About","Favorites","Settings")

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 60.dp, right = 25.dp)) {
        //invoking dropdown menu
        DropdownMenu(expanded = expand,

            onDismissRequest ={
                expand=false
                ShowDialouge.value = false
                              },
            modifier = Modifier
                .width(150.dp)
                .background(MaterialTheme.colorScheme.primary)) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(text= { Text(text, modifier = Modifier.clickable {
                    navController.navigate(
                        //we have to check which one has been  tapped
                        when (text){
                            "About" ->WeatherScreens.AboutScreen.name
                            "Favorites"->WeatherScreens.FavoriteScreen.name
                            else -> WeatherScreens.SettingsScreen.name
                        }
                    )
                },
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold)},
                    onClick = {
                    expand=false
                    ShowDialouge.value=false //when we click on eitherone i.e about , favorites etc then i want dialouge to disappear
                },
                    modifier = Modifier.padding(5.dp),
                    leadingIcon = {
                        when (text){
                        "About" -> Icon(Icons.Outlined.Info, contentDescription = "about icon")
                        "Favorites"->Icon(Icons.Outlined.Favorite, contentDescription = "favorite icon")
                        else -> Icon(Icons.Outlined.Settings, contentDescription = "settings icon")
                    }},
                    colors = MenuItemColors(textColor =MaterialTheme.colorScheme.inversePrimary,
                        leadingIconColor=MaterialTheme.colorScheme.inversePrimary,
                        trailingIconColor=MaterialTheme.colorScheme.inversePrimary,
                        disabledTextColor = MaterialTheme.colorScheme.inversePrimary,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.inversePrimary,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.inversePrimary

                        )
                    )
            }
        }
    }
    // Reset `expand` when ShowSettingDropDownMenu is shown
    if (ShowDialouge.value && !expand) { //when showdialouge is true then expand must be true otherwise button won't be clicked
        expand = true // Reset expand only when the dialog opens and expand is false
    }
}


