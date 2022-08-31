package com.example.yogamat.model

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MyYoga(
    @PrimaryKey
    val id: UUID,
    val title: String,
    val details: String,
    val image: String
)