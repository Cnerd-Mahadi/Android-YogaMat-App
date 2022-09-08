package com.example.yogamat.model

import androidx.room.PrimaryKey

data class Yoga(
    @PrimaryKey
    val id: Int,
    val title: String,
    val details: String,
    val image: String
)