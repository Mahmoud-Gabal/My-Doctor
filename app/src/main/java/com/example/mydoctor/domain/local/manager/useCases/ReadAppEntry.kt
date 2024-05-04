package com.example.mydoctor.domain.local.manager.useCases

import com.example.mydoctor.domain.local.manager.localUseManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUseManager: localUseManager
) {
    operator fun invoke() : Flow<Boolean> {
        return localUseManager.readAppEntry()
    }
}