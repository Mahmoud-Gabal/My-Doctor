package com.example.mydoctor.presentation.NavGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mydoctor.presentation.EmailCreatedScreen.createdEmailScreen
import com.example.mydoctor.presentation.ForgotPassword.forgotPasswordScreen
import com.example.mydoctor.presentation.HomeScreen.homeScreen
import com.example.mydoctor.presentation.SignInScreen.signInScreen
import com.example.mydoctor.presentation.SignUpScreen.signUpScreen
import com.example.mydoctor.presentation.onBOardingScreen.OnBoardingScreen
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
                 signInScreen(navController = navController)
             }

             composable(route = Routes.SignUp.route){
                 signUpScreen(navController = navController)
             }
             composable(route = Routes.ForgotPassword.route){
                 forgotPasswordScreen(navController = navController)
             }
             composable(route = Routes.CreatedEmailSuccessfully.route){
                 createdEmailScreen(navController = navController)
             }
         }
         navigation(route = Routes.App_Home.route, startDestination =Routes.HomeScreen.route ){
             composable(route = Routes.HomeScreen.route){
                 homeScreen(navController = navController)
             }
         }
     }
}