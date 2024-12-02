package com.example.weather.screens.SettingsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Unit
import com.example.weather.repository.WeatherDtabseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val reposiroty:WeatherDtabseRepo):ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            reposiroty.getallUnits()
                .distinctUntilChanged()
                .collect{listofUnits->

                    if(listofUnits.isNullOrEmpty()){
                        Log.d("Settings_db", "database for unit is empty: ")
                    }
                    else{
                        _unitList.value=listofUnits
                    }

                }

        }
    }

    fun insertUnit(unit:Unit) = viewModelScope.launch {
        reposiroty.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch {
        reposiroty.updateUnit(unit)
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        reposiroty.deleteUnit(unit)
    }

    fun deleteallunit()=viewModelScope.launch {
        reposiroty.deleteallUnit()
    }
}