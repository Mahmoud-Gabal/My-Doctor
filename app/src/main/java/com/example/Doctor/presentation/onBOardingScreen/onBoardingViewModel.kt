package com.example.Doctor.presentation.onBOardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Doctor.domain.local.manager.useCases.AppEntryCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class onBoardingViewModel @Inject constructor(
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