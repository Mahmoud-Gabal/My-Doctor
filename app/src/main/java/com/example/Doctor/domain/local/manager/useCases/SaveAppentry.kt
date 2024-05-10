package com.example.Doctor.domain.local.manager.useCases

import com.example.Doctor.domain.local.manager.localUseManager

class SaveAppEntry (
    private val localUseManager: localUseManager
){
    suspend operator fun invoke(){
        localUseManager.saveAppEntry()
    }
}