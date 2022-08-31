package com.example.yogamat.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "yoga_database"

@Database(entities = [MyYoga:: class], version = 1)

abstract class YogaDatabase: RoomDatabase() {

    abstract fun yogaDao(): MyYogaDao

    companion object {

        @Volatile
        private var INSTANCE: YogaDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            initiateDB(context).also {
                INSTANCE = it
            }
        }

        private fun initiateDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            YogaDatabase:: class.java,
            DB_NAME
        ).build()

        fun getDB() = INSTANCE ?: throw IllegalStateException("DB not initiated")
    }
}