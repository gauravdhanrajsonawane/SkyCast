package com.example.skycast.screens.settings

import androidx.lifecycle.ViewModel
import com.example.skycast.model.Unit
import com.example.skycast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
): ViewModel(){

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getUnits().distinctUntilChanged()
//                .collect() { listOfUnits ->
//                    if (listOfUnits.isNullOrEmpty()) {
//                        Log.d("Tag",": Empty list")
//                    }else {
//                        _unitList.value = listOfUnits
//                        //Log.d("FAVS",":${favList.value}")
//                    }
//
//                }
//        }
//    }
//
//    fun insertUnit(unit: Unit) = viewModelScope.launch { repository.insertUnit(unit) }
//    fun updateUnit(unit: Unit) = viewModelScope.launch { repository.updateUnit(unit) }
//    fun deleteUnit(unit: Unit) = viewModelScope.launch { repository.deleteUnit(unit) }
//    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
}


