package com.example.yogamat.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yogamat.R
import com.example.yogamat.model.MyYoga
import com.example.yogamat.model.Yoga
import com.example.yogamat.model.YogaDatabase
import com.example.yogamat.model.YogaRepo
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MyYogaListViewModel(
    private val application: Application
): ViewModel() {

    val yogaRepo  = YogaRepo(YogaDatabase.getDB(), application.applicationContext)
    private var _yogaList: MutableStateFlow<List<MyYoga>> = MutableStateFlow(emptyList())
    val yogaList
    get() = _yogaList.asStateFlow()

    init {

        viewModelScope.launch {
            yogaRepo.getAllYoga().collect{
                _yogaList.value = it
            }
        }
    }




}