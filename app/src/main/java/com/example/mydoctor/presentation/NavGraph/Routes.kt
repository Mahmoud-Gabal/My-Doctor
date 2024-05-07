package com.example.mydoctor.presentation.NavGraph

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

}