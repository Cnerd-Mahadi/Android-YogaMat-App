package com.example.yogamat.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

@Suppress("UNCHECKED_CAST")
class MyYogaListViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyYogaListViewModel(application) as T
    }
}