package com.example.Doctor.presentation.NavGraph

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.Doctor.presentation.EmailCreatedScreen.createdEmailScreen
import com.example.Doctor.presentation.ForgotPassword.forgotPasswordScreen
import com.example.Doctor.presentation.HomeScreen.homeScreen
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.GoogleAuthUiClient
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.SignInViewModel
import com.example.Doctor.presentation.SignInScreen.signInScreen
import com.example.Doctor.presentation.SignUpScreen.signUpScreen
import com.example.Doctor.presentation.onBOardingScreen.OnBoardingScreen
import com.example.Doctor.presentation.onBOardingScreen.onBoardingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavGraph(
    startDestination : String,
    googleAuthUiClient : GoogleAuthUiClient,
    lifecycleScope : LifecycleCoroutineScope,
    context: Context
){
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
                 val viewModel = viewModel<SignInViewModel>()
                 val state by viewModel.state.collectAsStateWithLifecycle()

                 LaunchedEffect(key1 = Unit) {
                     if(googleAuthUiClient.getSignedInUser() != null) {
                         navController.navigate(Routes.App_Home.route)
                     }
                 }

                 val launcher = rememberLauncherForActivityResult(
                     contract = ActivityResultContracts.StartIntentSenderForResult(),
                     onResult = { result ->
                         if(result.resultCode == RESULT_OK) {
                             lifecycleScope.launch {
                                 val signInResult = googleAuthUiClient.signInWithIntent(
                                     intent = result.data ?: return@launch
                                 )
                                 viewModel.onSignInResult(signInResult)
                             }
                         }
                     }
                 )

                 LaunchedEffect(key1 = state.isSignInSuccessful) {
                     if(state.isSignInSuccessful) {
                         Toast.makeText(
                             context,
                             "Signed in successfully",
                             Toast.LENGTH_LONG
                         ).show()

                         navController.navigate(Routes.App_Home.route)
                         viewModel.resetState()
                     }
                 }

                 signInScreen(
                     navController = navController,
                     state = state,
                     onSignInClick = {
                         lifecycleScope.launch {
                             val signInIntentSender = googleAuthUiClient.signIn()
                             launcher.launch(
                                 IntentSenderRequest.Builder(
                                     signInIntentSender ?: return@launch
                                 ).build()
                             )
                         }
                     }
                 )
             }

             composable(route = Routes.SignUp.route){
                 signUpScreen(
                     navController = navController
                 )
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
                 homeScreen(
                     navController = navController,
                     userData = googleAuthUiClient.getSignedInUser(),
                     onSignOut = {
                         lifecycleScope.launch {
                             googleAuthUiClient.signOut()
                             Toast.makeText(
                                 context,
                                 "Signed out successfully",
                                 Toast.LENGTH_LONG
                             ).show()

                         }
                     }
                 )

             }
         }
     }
}