package com.example.Doctor.presentation.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Doctor.R
import com.example.Doctor.data.local.doctors.AllDoctorsList
import com.example.Doctor.data.local.doctors.CardiologistsList
import com.example.Doctor.data.local.doctors.DentistsList
import com.example.Doctor.data.local.doctors.DermatologistesList
import com.example.Doctor.data.local.doctors.InternistsList
import com.example.Doctor.data.local.doctors.NeurologistsList
import com.example.Doctor.data.local.doctors.O_R_LsList
import com.example.Doctor.data.local.doctors.ObstetriciansList
import com.example.Doctor.data.local.doctors.OculistsList
import com.example.Doctor.data.local.doctors.PediatriciansList
import com.example.Doctor.data.local.doctors.PsychiatristsList
import com.example.Doctor.data.local.doctors.PsychologistsList
import com.example.Doctor.data.local.doctors.RheumatologistsList
import com.example.Doctor.domain.local.db.DoctorDao
import com.example.Doctor.domain.local.db.bookmarkedDRs
import com.example.Doctor.domain.local.manager.useCases.AppEntryCases
import com.example.Doctor.presentation.NavGraph.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryCases: AppEntryCases,
) : ViewModel(){
    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Routes.App_OnBoard.route)
    val startDestination: State<String> = _startDestination


    init {
        appEntryCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value =  Routes.App_Start_Signing.route
            }else{
                _startDestination.value = Routes.App_OnBoard.route
            }
            delay(400) //Without this delay, the onBoarding screen will show for a momentum.
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

}