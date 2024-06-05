package com.example.Doctor.presentation.NavGraph

import okhttp3.Route

sealed class Routes(
    val route : String
) {
    object App_OnBoard : Routes("App_OnBoard")
    object BoardingScreen : Routes("onBoardingScreen")
    object App_Start_Signing : Routes("App_Start")

    object signIn : Routes("SignIn")
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("Forgot_Password")
    object CreatedEmailSuccessfully  :Routes("Created_Email_Successfully")
    object App_Home : Routes("App_Home")
    object HomeScreen : Routes("HomeScreen")
    object originHomeScreen : Routes("originHomeScreen")




    object OculistsScreen : Routes("Oculists")
    object CardiologistsScreen : Routes("Cardiologists")
    object PsychiatristsScreen : Routes("Psychiatrists")
    object RheumatologistsScreen : Routes("Rheumatologists")
    object NeurologistsScreen : Routes("Neurologists")
    object ObstetriciansScreen : Routes("Obstetricians")
    object O_R_LsScreen : Routes("O_R_Ls")
    object PsychologistsScreen : Routes("Psychologists")
    object PediatriciansScreen : Routes("Pediatricians")
    object DermatologistesScreen : Routes("Dermatologiste")
    object DentistsScreen : Routes("Dentists")
    object InternistsScreen : Routes("Internists")
    object AllDoctorsScreen  :Routes("AllDoctors")
    object AboutDoctor  :Routes("aboutDoctor")
    object AllTopRatedDoctorsScreen : Routes("AllTopRatedDoctors")
    object savedDocotrsScreen : Routes("saved_Docotrs")

}