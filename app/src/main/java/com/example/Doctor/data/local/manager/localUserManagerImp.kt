package com.example.Doctor.data.local.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.Doctor.domain.local.manager.localUseManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class localUserManagerImp(
    private val context: Context
) : localUseManager {
    override suspend fun saveAppEntry() {
        context.datastore.edit {settings ->
            settings[preferencesKeys.App_Key] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {Preferences ->
            Preferences[preferencesKeys.App_Key] ?: false
        }
    }
}

private val Context.datastore : DataStore<Preferences> by preferencesDataStore("settings")

private object preferencesKeys{
    val App_Key = booleanPreferencesKey("App_key")
}