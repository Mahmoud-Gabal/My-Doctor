package com.example.Doctor.presentation.onBOardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Doctor.data.local.doctors.AllDoctorsList
import com.example.Doctor.domain.local.db.DoctorDao
import com.example.Doctor.domain.local.manager.useCases.AppEntryCases
import com.example.Doctor.presentation.HomeScreen.DoctorInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class onBoardingViewModel @Inject constructor(
    private val appEntryCases: AppEntryCases,
    private val dao : DoctorDao
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
    fun addAllDoctorstoDB(list : List<DoctorInfo> = AllDoctorsList){
        viewModelScope.launch {
            dao.addListOfDoctor(list)
        }
    }
}