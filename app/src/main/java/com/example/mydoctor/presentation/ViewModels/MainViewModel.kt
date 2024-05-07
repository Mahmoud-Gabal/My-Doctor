package com.example.mydoctor.presentation.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.domain.local.manager.useCases.AppEntryCases
import com.example.mydoctor.presentation.NavGraph.Routes
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryCases: AppEntryCases
) : ViewModel(){
    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Routes.App_OnBoard.route)
    val startDestination: State<String> = _startDestination

    init {
        appEntryCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value = if (FirebaseAuth.getInstance().currentUser !=null) Routes.App_Home.route else Routes.App_Start_Signing.route
            }else{
                _startDestination.value = Routes.App_OnBoard.route
            }
            delay(200) //Without this delay, the onBoarding screen will show for a momentum.
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}