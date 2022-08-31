package com.example.yogamat.model

import com.example.yogamat.adapter.ListYogaAdapter
import kotlinx.coroutines.flow.Flow
import java.util.*

class YogaRepo(
    private val yogaDatabase: YogaDatabase
) {

    suspend fun save(yoga: MyYoga) = yogaDatabase.yogaDao().save(yoga)

    suspend fun delete(yoga: MyYoga) = yogaDatabase.yogaDao().delete(yoga)

    suspend fun getYoga(id: UUID) = yogaDatabase.yogaDao().getYoga(id)

    fun getAllYoga(): Flow<List<MyYoga>> = yogaDatabase.yogaDao().getAllYoga()

    suspend fun deleteAllYoga() = yogaDatabase.yogaDao().deleteAllYoga()
}