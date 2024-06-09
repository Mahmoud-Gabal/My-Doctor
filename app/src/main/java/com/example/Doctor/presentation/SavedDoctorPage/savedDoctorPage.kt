package com.example.Doctor.presentation.SavedDoctorPage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.Doctor.R
import com.example.Doctor.presentation.HomeScreen.filterBooking
import com.example.Doctor.presentation.HomeScreen.savedDoctorCard
import com.example.Doctor.presentation.HomeScreen.shimmerDoctorCard
import com.example.Doctor.presentation.NewsPaperPage.shimmerCard
import com.example.Doctor.presentation.NewsPaperPage.shimmerEffect
import com.example.Doctor.presentation.ViewModels.BookViewModel
import com.example.Doctor.presentation.ViewModels.BookingEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Preview
@Composable
fun savedDoctorsPage(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    bookViewModel: BookViewModel = viewModel()
) {
    val bookModel = bookViewModel
    val searchText = bookModel.searchingText.collectAsState()
    val bookedList = bookModel.filtered_list.collectAsState()
    val doctors = bookedList.value
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        val focusManager = LocalFocusManager.current
        TextField(
            value = searchText.value,
            onValueChange = {
                bookModel.changeSearchTextTo(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(20.dp)),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
            }), placeholder = {
                Text(text = "Search", color = colorResource(id = R.color.blueGrey))
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
        if (doctors == null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 10.dp, bottom = 70.dp)
            ) {
                items(10) { shimmerDoctorCard ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        shimmerDoctorCard()
                        Box(modifier = Modifier.size(20.dp).shimmerEffect())
                    }
                }
            }

        } else {
            if (doctors.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 70.dp)
                ) {
                    items(doctors) { doctorInfo ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            savedDoctorCard(info = doctorInfo, navController = navController)
                            Icon(
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        bookModel.onBookingEvent(
                                            BookingEvents.cancelBookingDR(
                                                doctorInfo
                                            )
                                        )
                                    },
                                imageVector = Icons.Filled.Delete,
                                contentDescription = null,
                                tint = colorResource(id = R.color.darkBlue),
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "No doctors found")


                }
            }
        }

    }


}
