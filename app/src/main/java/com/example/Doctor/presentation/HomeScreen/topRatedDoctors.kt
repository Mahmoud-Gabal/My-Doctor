package com.example.Doctor.presentation.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
        contentPadding = PaddingValues(top = 10.dp, bottom = 70.dp)
    ){
        items(topRated){ doctorInfo ->
            doctorCard(info = doctorInfo , navController = navController)
        }
    }
}


fun getTopRated(doctors : List<DoctorInfo>) : MutableList<DoctorInfo> {
    val topList = mutableListOf<DoctorInfo>()

    for (i in doctors.indices){
        if (doctors[i].stars>=3) topList.add(doctors[i])
    }
    return topList
}