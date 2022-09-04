package com.example.yogamat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.yogamat.Event

class RouteUtilViewModel: ViewModel() {

    val currentNavController = MutableLiveData<Event<NavController>>()

    val tabVisibility = MutableLiveData<Boolean>().apply {
        this.value = false
    }
}