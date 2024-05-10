package com.example.Doctor.presentation.onBOardingScreen.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Doctor.ui.theme.MyDoctorTheme

@Composable
fun welcomePage(
    modifier: Modifier = Modifier
) {
    Column( verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "Welcome to\n" +
                    "My Doctor",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            lineHeight = 45.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Find a specialist that you only need on a consulting application",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 35.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun welcomePagePreview() {
    MyDoctorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            welcomePage()
        }
    }
}