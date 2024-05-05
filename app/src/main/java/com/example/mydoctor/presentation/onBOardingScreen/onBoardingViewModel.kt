package com.example.mydoctor.presentation.onBOardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.domain.local.manager.useCases.AppEntryCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class onBoardingViewModel(
    private val appEntryCases: AppEntryCases
) :  ViewModel() {


    fun omEvent(event: onBoardingEvents){
        when(event){
            is onBoardingEvents.saveAppEntry -> saveEntry()
        }
    }

    private fun saveEntry() {
        viewModelScope.launch {
            appEntryCases.saveAppEntry()
        }
    }
}