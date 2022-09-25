package com.example.yogamat.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yogamat.R
import com.example.yogamat.model.Yoga
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*

class YogaListViewModel(
    application: Application
): AndroidViewModel(application) {

    private var _yogaList = mutableListOf<Yoga>()
    val yogaList
        get() = _yogaList

    init {
        val yogaTitleString = application.resources.getStringArray(R.array.yoga_title)
        val yogaDetailsString = application.resources.getStringArray(R.array.yoga_details)
        val yogaImageString = application.resources.getStringArray(R.array.yoga_images)

        for (i in yogaDetailsString.indices) {
            val yoga = Yoga(i, yogaTitleString[i], yogaDetailsString[i], yogaImageString[i])
            yogaList.add(yoga)
        }
        val sharedData = application.getSharedPreferences("data", Context.MODE_PRIVATE)
        val sharedDataToJson = Gson().toJson(yogaList)
        sharedData.edit().apply {
            putString("yogaData", sharedDataToJson)
            apply()
        }
    }


}