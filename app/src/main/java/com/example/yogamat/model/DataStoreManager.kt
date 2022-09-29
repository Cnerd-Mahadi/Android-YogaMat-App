package com.example.yogamat.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("PREFERENCE_DATA")
        private val yogaDataKey = stringPreferencesKey("YOGA_DATA_KEY")
    }

    fun getData(): Flow<String?> {
       return context.dataStore.data.map {
           it[yogaDataKey]
       }
    }

    suspend fun setData(yogaData: String) {
        context.dataStore.edit {
            it[yogaDataKey] = yogaData
        }
    }

}