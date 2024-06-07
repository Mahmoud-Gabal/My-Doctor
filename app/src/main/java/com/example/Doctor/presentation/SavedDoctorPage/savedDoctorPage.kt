package com.example.Doctor.presentation.SavedDoctorPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.Doctor.R
import com.example.Doctor.presentation.HomeScreen.savedDoctorCard
import com.example.Doctor.presentation.ViewModels.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun savedDoctorsPage(
    modifier: Modifier = Modifier,
//    doctors: List<bookmarkedDRs>,
    navController: NavHostController
) {
    val bookViewModel : BookViewModel = hiltViewModel()
    val bookedList = bookViewModel.bookedList.collectAsState()
    val doctors = bookedList.value
    if (doctors.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            var searchText by rememberSaveable {
                mutableStateOf("")
            }
            val focusManager = LocalFocusManager.current
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(20.dp)),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                }), placeholder = {
                    Text("Search", color = colorResource(id = R.color.blueGrey))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = colorResource(id = R.color.blueGrey)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = colorResource(id = R.color.searchBar),
                    focusedContainerColor = colorResource(id = R.color.searchBar),
                    errorContainerColor = colorResource(id = R.color.searchBar),
                    cursorColor = Color.White,
                    errorCursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    errorTextColor = Color.White
                )
            )
            LazyColumn(
//            modifier = modifier
//                .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 10.dp, bottom = 70.dp)
            ) {
                items(doctors) { doctorInfo ->
                    savedDoctorCard(info = doctorInfo, navController = navController)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No doctors saved")
        }
    }


}