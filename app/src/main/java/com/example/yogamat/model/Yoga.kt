package com.example.yogamat.model

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Yoga(
    @PrimaryKey
    val id: Int,
    val title: String,
    val details: String,
    val image: String
)