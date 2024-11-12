package com.example.weather.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.widgets.WeatherAppbar

//Top bar for Search Screen
@Composable
fun SearchScreen (navController: NavController){
    Scaffold(topBar = {
        WeatherAppbar(title = "Search",
            navController = navController,
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            isMainScreen = false){
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.padding(it)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                SearchBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)){country-> //we are getting country from the user

                  //  Log.d("TAG", "SearchScreen: $country")
                }
            }
        }
    }
}


//search bar design
@Composable
fun SearchBar(
    modifier: Modifier=Modifier,
    onSearch: (String) -> Unit= {} //it's last parameter so we can use trailing lamda
){
    //when we rotate the phone then the things written in text field will not be gone if we use
    // rememberSaveable
    val searchQueryState = rememberSaveable {
        mutableStateOf("")//it will be empty first then will hold the city/country name
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(searchQueryState.value) { //we are checking searchQueryState it's empty or not
        searchQueryState.value.trim().isNotEmpty()
    }
    Column {
        CommonTextField(
            valueState= searchQueryState,
            placeholder="City Name",
            onAction=KeyboardActions{
                if(!valid)
                    return@KeyboardActions

                    onSearch(searchQueryState.value.trim()) //we are passing the country on OnSearch that we wrote using keyboard
                searchQueryState.value = "" //removing the previously entered text form text field
                keyboardController?.hide()
            }
        )
    }
}


//we are using OutlinedTextField for the design
//in short words it's input text field.
@Composable
fun CommonTextField(valueState: MutableState<String>,
                    placeholder: String,
                    keyboardType:KeyboardType =KeyboardType.Text,
                    imeActions:ImeAction = ImeAction.Next, //in keyboard it shows next button at right end
                    onAction:KeyboardActions=KeyboardActions.Default){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value=it},
        label = { Text(text = placeholder) },// the text written in label is displayed inside the container
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType, imeAction = imeActions),
        keyboardActions= onAction,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary ,
            cursorColor = MaterialTheme.colorScheme.inversePrimary),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp))

}