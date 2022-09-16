package com.example.yogamat.model

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.util.*

class YogaRepo(
    private val yogaDatabase: YogaDatabase,
    private val yogaAppContext: Context
) {

    suspend fun save(yoga: MyYoga) = yogaDatabase.yogaDao().save(yoga)

    suspend fun delete(yoga: MyYoga) = yogaDatabase.yogaDao().delete(yoga)

    suspend fun getYoga(id: UUID) = yogaDatabase.yogaDao().getYoga(id)

    fun getAllYoga(): Flow<List<MyYoga>> = yogaDatabase.yogaDao().getAllYoga()

    suspend fun deleteAllYoga() = yogaDatabase.yogaDao().deleteAllYoga()

    private fun getImageFile(yoga: MyYoga): File = File(yogaAppContext.filesDir, yoga.photoFileName)

    fun getImageUri(yoga: MyYoga): Uri = FileProvider.getUriForFile(yogaAppContext, "com.example.yogamat.fileProvider",
        getImageFile(yoga))

}