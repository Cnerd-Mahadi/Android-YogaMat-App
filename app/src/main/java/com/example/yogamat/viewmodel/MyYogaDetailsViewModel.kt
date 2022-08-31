package com.example.yogamat.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yogamat.model.MyYoga
import com.example.yogamat.model.Yoga
import com.example.yogamat.model.YogaDatabase
import com.example.yogamat.model.YogaRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MyYogaDetailsViewModel(
    private val id: UUID,
): ViewModel() {

    private val yogaRepo  = YogaRepo(YogaDatabase.getDB())
    private var _yoga: MutableStateFlow<MyYoga?> = MutableStateFlow(null)
    val yoga
        get() = _yoga


    init {

        viewModelScope.launch {
            _yoga.value =  yogaRepo.getYoga(id)
        }

    }

}