package com.example.mydoctor.domain.local.manager

import kotlinx.coroutines.flow.Flow

interface localUseManager {
    suspend fun saveAppEntry()
    fun readAppEntry() : Flow<Boolean>
}
