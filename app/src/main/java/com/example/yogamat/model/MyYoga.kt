package com.example.yogamat.model

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MyYoga(
    @PrimaryKey
    val id: UUID,
    var title: String = "",
    var details: String = "",
    var image: String = ""
) {
    val photoFileName: String
    get() = "IMG_$id"
}