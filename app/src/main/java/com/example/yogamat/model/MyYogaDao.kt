package com.example.yogamat.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface MyYogaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(yoga: MyYoga)

    @Delete
    suspend fun delete(yoga: MyYoga)

    @Query("SELECT * FROM myyoga where id = :id")
    suspend fun getYoga(id: UUID): MyYoga

    @Query("SELECT * FROM myyoga")
    fun getAllYoga(): Flow<List<MyYoga>>

    @Query("DELETE FROM myyoga")
    suspend fun deleteAllYoga()
}