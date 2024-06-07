package com.example.Doctor.presentation.NavGraph

import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
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
import com.example.Doctor.domain.local.db.bookmarkedDRs
import com.example.Doctor.presentation.EmailCreatedScreen.createdEmailScreen
import com.example.Doctor.presentation.ForgotPassword.forgotPasswordScreen
import com.example.Doctor.presentation.HomeScreen.DoctorInfo
import com.example.Doctor.presentation.HomeScreen.aboutDoctor
import com.example.Doctor.presentation.HomeScreen.doctorsPage
import com.example.Doctor.presentation.HomeScreen.getTopRated
import com.example.Doctor.presentation.HomeScreen.homeScreen
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.GoogleAuthUiClient
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.SignInViewModel
import com.example.Doctor.presentation.SignInScreen.signInScreen
import com.example.Doctor.presentation.SignUpScreen.signUpScreen
import com.example.Doctor.presentation.ViewModels.NewsViewModel
import com.example.Doctor.presentation.onBOardingScreen.OnBoardingScreen
import com.example.Doctor.presentation.onBOardingScreen.onBoardingViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
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
                 OnBoardingScreen (onEvent = viewModel::omEvent,addDB = viewModel::addAllDoctorstoDB)
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
             composable(route = Routes.CardiologistsScreen.route){
                 doctorsPage(doctors = CardiologistsList, navController = navController)
             }
             composable(route = Routes.DentistsScreen.route){
                 doctorsPage(doctors = DentistsList, navController = navController)
             }
             composable(route = Routes.DermatologistesScreen.route){
                 doctorsPage(doctors = DermatologistesList, navController = navController)
             }
             composable(route = Routes.InternistsScreen.route){
                 doctorsPage(doctors = InternistsList, navController = navController)
             }
             composable(route = Routes.NeurologistsScreen.route){
                 doctorsPage(doctors = NeurologistsList, navController = navController)
             }
             composable(route = Routes.O_R_LsScreen.route){
                 doctorsPage(doctors = O_R_LsList, navController = navController)
             }
             composable(route = Routes.ObstetriciansScreen.route){
                 doctorsPage(doctors = ObstetriciansList, navController = navController)
             }
             composable(route = Routes.OculistsScreen.route){
                 doctorsPage(doctors = OculistsList, navController = navController)
             }
             composable(route = Routes.PediatriciansScreen.route){
                 doctorsPage(doctors = PediatriciansList, navController = navController)
             }
             composable(route = Routes.PsychiatristsScreen.route){
                 doctorsPage(doctors = PsychiatristsList, navController = navController)
             }
             composable(route = Routes.PsychologistsScreen.route){
                 doctorsPage(doctors = PsychologistsList , navController = navController)
             }
             composable(route = Routes.RheumatologistsScreen.route){
                 doctorsPage(doctors = RheumatologistsList, navController = navController)
             }
             composable(route = Routes.AllDoctorsScreen.route){
                 doctorsPage(doctors = AllDoctorsList, navController = navController)
             }
             composable(route = Routes.savedDocotrsScreen.route){
//                 savedDoctorsPage(doctors = listOf<bookmarkedDRs>() , navController = navController)
             }
             composable(
                 route = Routes.AboutDoctor.route + "/{name}/{job}/{stars}/{reviews}/{exp}/{about}/{img}/{address}",
                 arguments = listOf(
                     navArgument(name = "name"){type = NavType.StringType},
                     navArgument(name = "job"){type = NavType.StringType},
                     navArgument(name = "stars"){type = NavType.IntType},
                     navArgument(name = "reviews"){type = NavType.IntType},
                     navArgument(name = "exp"){type = NavType.IntType},
                     navArgument(name = "about"){type = NavType.StringType},
                     navArgument(name = "img"){type = NavType.IntType},
                     navArgument(name = "address"){type = NavType.StringType},
                 )
             ){entry ->
                 val name = entry.arguments?.getString("name")
                 val job = entry.arguments?.getString("job")
                 val stars = entry.arguments?.getInt("stars")
                 val reviews = entry.arguments?.getInt("reviews")
                 val exp = entry.arguments?.getInt("exp")
                 val about = entry.arguments?.getString("about")
                 val img = entry.arguments?.getInt("img")
                 val address = entry.arguments?.getString("address")
                 val info = DoctorInfo(name ?: "", job ?: "",stars ?: 0,reviews ?: 0,exp ?: 0,about ?: "",img ?: 0,address ?: "")
                 aboutDoctor(navController = navController, info = info)
             }
             composable(route = Routes.AllTopRatedDoctorsScreen.route){
                 doctorsPage(doctors = getTopRated(AllDoctorsList),navController = navController)
             }
         }
     }
}