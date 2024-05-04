package com.example.mydoctor.domain.local.manager.useCases

import com.example.mydoctor.domain.local.manager.localUseManager

class SaveAppEntry (
    private val localUseManager: localUseManager
){
    suspend operator fun invoke(){
        localUseManager.saveAppEntry()
    }
}