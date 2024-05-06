package com.example.mydoctor.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mydoctor.presentation.NavGraph.NavGraph
import com.example.mydoctor.presentation.SignUpScreen.signUpScreen
import com.example.mydoctor.presentation.ViewModels.MainViewModel
import com.example.mydoctor.presentation.onBOardingScreen.OnBoardingScreen
import com.example.mydoctor.ui.theme.MyDoctorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),Color.Transparent.toArgb()
            )
        )
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { viewModel.splashCondition.value})
        }
        setContent {
            MyDoctorTheme {
                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDoctorTheme {
        Greeting("Android")
    }
}