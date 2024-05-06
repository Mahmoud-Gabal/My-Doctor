package com.example.mydoctor.presentation.NavGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.example.mydoctor.presentation.SignInScreen.signInScreen
import com.example.mydoctor.presentation.SignUpScreen.signUpScreen
import com.example.mydoctor.presentation.ViewModels.MainViewModel
import com.example.mydoctor.presentation.onBOardingScreen.OnBoardingScreen
import com.example.mydoctor.presentation.onBOardingScreen.onBoardingEvents
import com.example.mydoctor.presentation.onBOardingScreen.onBoardingViewModel

@Composable
fun NavGraph(
    startDestination : String,
) {
    val navController = rememberNavController()
     NavHost(navController = navController, startDestination = startDestination) {

         navigation(route = Routes.App_OnBoard.route, startDestination =Routes.BoardingScreen.route ){
             composable(route = Routes.BoardingScreen.route){
                 val viewModel : onBoardingViewModel = hiltViewModel()
                 OnBoardingScreen (onEvent = viewModel::omEvent)
             }
         }

         navigation(route = Routes.App_Start_Signing.route,startDestination = Routes.signIn.route){
             composable(route = Routes.signIn.route){
                 signInScreen()
             }

             composable(route = Routes.SignUp.route){
                 signUpScreen()
             }
         }

     }
}