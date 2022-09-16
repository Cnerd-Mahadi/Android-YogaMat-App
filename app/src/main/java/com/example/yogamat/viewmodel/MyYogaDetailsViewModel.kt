package com.example.yogamat.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yogamat.model.MyYoga
import com.example.yogamat.model.YogaDatabase
import com.example.yogamat.model.YogaRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class MyYogaDetailsViewModel(
    private val id: UUID,
    application: Application
): ViewModel() {

    val yogaRepo  = YogaRepo(YogaDatabase.getDB(), application.applicationContext)
    private val _yoga: MutableStateFlow<MyYoga?> = MutableStateFlow(null)
    val yoga =  _yoga.asStateFlow()


    init {

        viewModelScope.launch {
            _yoga.value =  yogaRepo.getYoga(id)

        }



    }

}