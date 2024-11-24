package com.example.weather.screens.FavScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Favorites
import com.example.weather.repository.WeatherDtabseRepo
import com.example.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewmodel @Inject constructor(private val repository: WeatherDtabseRepo):ViewModel() { //inherit from viewmodel
    private val _favlist = MutableStateFlow<List<Favorites>>(emptyList())
    val favlist = _favlist.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) { //we are making sure our highway have much more lanes
            //so that more cars can run  so we used Dispatchers.IO
            repository.getAllFav()
                .distinctUntilChanged()
                .collect{listoffav->
                    if (listoffav.isNullOrEmpty()){
                        Log.d("REPO", "Empty fav:")
                    }
                    else{
                        _favlist.value=listoffav
                    }
                }
        }
    }

    fun insertfav(fav:Favorites)=viewModelScope.launch {
        repository.insertFav(fav)
    }

    fun updatefav(fav:Favorites)=viewModelScope.launch {
        repository.updateFav(fav)
    }

    fun deletefav(fav:Favorites)=viewModelScope.launch {
        repository.deletefav(fav)
    }

    fun deletefavall()=viewModelScope.launch {
        repository.deleteall()
    }

    fun getfavbyid(city:String)=viewModelScope.launch {
        repository.getFavbyId(city)
    }
}