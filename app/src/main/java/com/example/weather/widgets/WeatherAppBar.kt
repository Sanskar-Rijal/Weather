package com.example.weather.widgets

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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

    //creating state for 3 dots to show favorite, setting and etc
    val showDialog = remember{
        mutableStateOf(false) //when we click on the icon then we want it to be true
    }

    if(showDialog.value){
        ShowSettingDropDownMenu(ShowDialouge=showDialog,navController=navController)
    }


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

//for show dialouge

@Composable
fun ShowSettingDropDownMenu(ShowDialouge: MutableState<Boolean>, navController: NavController) {

    var expand by remember {
        mutableStateOf(true)
    }

    val items = listOf("About","Favorites","Settings")

    Column(modifier = Modifier.fillMaxWidth()
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
                DropdownMenuItem(text= { Text(text, modifier = Modifier.clickable {  },
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


