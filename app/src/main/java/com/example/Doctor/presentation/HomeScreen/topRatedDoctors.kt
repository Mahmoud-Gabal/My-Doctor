package com.example.Doctor.presentation.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun topDoctors(
    modifier: Modifier = Modifier,
    topRated : List<DoctorInfo>,
    navController: NavHostController
) {
    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ){
        items(topRated){ doctorInfo ->
            doctorCard(info = doctorInfo , navController = navController)
        }
    }
}