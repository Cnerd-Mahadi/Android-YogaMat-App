package com.example.yogamat.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class YogaDetailsViewModel(
    private val id: Int,
    application: Application
): ViewModel() {

    private var _yogaList = mutableListOf<Yoga>()
    val yogaList
        get() = _yogaList
    private var _yoga: Yoga? = null
    val yoga
    get() = _yoga

    init {

        val itemType =  object : TypeToken<List<Yoga>>(){}.type
        val data = Gson().fromJson<List<Yoga>>(application.getSharedPreferences("data", Context.MODE_PRIVATE)
            .getString("yogaData", null), itemType)
        _yoga = data[id]
    }

}