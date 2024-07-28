package com.example.Doctor.presentation.EmailCreatedScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Doctor.R
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.ui.theme.MyDoctorTheme

@Composable
fun createdEmailScreen(
    modifier: Modifier = Modifier,
    navController : NavHostController
) {
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(5.dp)),
            painter = painterResource(id = R.drawable.medicalteam),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(text = "My Doctor", fontWeight = FontWeight.Bold)
        Text(text = "Email created successfully!", fontSize = 25.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Login now?")
            Text(
                text = "sign in",
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.ButtonColor),
                modifier = Modifier.clickable(enabled = true){
                    navController.navigate(Routes.signIn.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun createdEmailScreenPreview(){
    MyDoctorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            createdEmailScreen()
        }
    }
}