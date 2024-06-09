package com.example.Doctor.presentation.HomeScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.Doctor.R
import com.example.Doctor.domain.local.db.bookmarkedDRs

import com.example.Doctor.presentation.HomeScreen.calender.CalendarApp
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.presentation.ViewModels.BookViewModel
import com.example.Doctor.presentation.ViewModels.BookingEvents
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun aboutDoctor(
    modifier: Modifier = Modifier,
    info: DoctorInfo = DoctorInfo(
        "Mahmoud Gabal",
        "Bone Specialist",
        4,
        40,
        3,
        "I am Mahmoud,i have 4 years experience in worjing as bone specialist,i work in daamiette," +
                ".I work in Alazhar hospital,i studied in Faculty of medicine in Damiette university"
                + ".I love my work and do my best seeking to help my patients.",
        R.drawable.doc, "Cairo"
    ),
    navController: NavHostController = rememberNavController(),
    bookViewModel: BookViewModel = hiltViewModel(),

    ) {
    val booked_info = bookmarkedDRs(
        info.name,
        info.job,
        info.stars,
        info.reviews,
        info.exp,
        info.about,
        info.img, info.address
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = colorResource(id = R.color.splashBackgroundTr)
                ),
                actions = {
                    val bookModel = bookViewModel
                    val bookedList = bookModel.filtered_list.collectAsState()
                    if (filterBooking(bookedList.value!!,booked_info).isNotEmpty()){
                            Icon(
                                modifier = Modifier
                                    .padding(end = 20.dp)
                                    .size(35.dp)
                                    .clickable {
                                        bookViewModel.onBookingEvent(
                                            BookingEvents.cancelBookingDR(
                                                filterBooking(bookedList.value!!, booked_info)[0]
                                            )
                                        )
                                    },
                                imageVector = Icons.Filled.Bookmark,
                                contentDescription = null,
                                tint = Color.White,
                            )
                    }else{
                        Icon(
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .size(35.dp)
                                .clickable {
                                    bookViewModel.onBookingEvent(
                                        BookingEvents.bookDR(
                                            booked_info
                                        )
                                    )
                                },
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
            )
        },
        containerColor = Color(236, 236, 236)

    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        )
        {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.25f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.9f)
                            .clip(RoundedCornerShape(bottomStart = 40.dp))
                            .background(color = colorResource(id = R.color.splashBackgroundTr))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                            .align(Alignment.BottomCenter),
                    ) {
                        if (info.img == 0) {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(160.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(Color.Gray)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.doc),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(160.dp)
                                    .clip(RoundedCornerShape(15.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                text = "Dr.${info.name}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                            )

                            Text(
                                text = info.job,
                                color = colorResource(id = R.color.focusedtextField)
                            )
                            Row(
                                modifier = Modifier.padding(top = 10.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(colorResource(id = R.color.focusedtextField)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "${info.stars}.0",
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Ratings",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.focusedtextField)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(colorResource(id = R.color.focusedtextField)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.portfolio),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "${info.exp} Years",
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Experience",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.focusedtextField)
                                    )
                                }
                            }
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    contentPadding = PaddingValues(vertical = 15.dp)
                ) {
                    item {
                        Column {
                            var isExpanded by rememberSaveable {
                                mutableStateOf(false)
                            }
                            Column(
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color.White)
                                    .padding(horizontal = 20.dp)
                                    .clickable {
                                        isExpanded = !isExpanded
                                    }
                            ) {
                                Text(
                                    text = "About the doctor ",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(vertical = 10.dp),
                                    color = colorResource(id = R.color.darkBlue),
                                    fontWeight = FontWeight.SemiBold
                                )
                                if (info.about.lastIndex > 130) {
                                    Text(
                                        text = buildAnnotatedString {
                                            if (isExpanded) append(info.about) else append(
                                                info.about,
                                                start = 0,
                                                end = 130
                                            )
                                            withStyle(SpanStyle(color = colorResource(id = R.color.splashBackgroundTr))) {
                                                append(if (isExpanded) "Show less" else "Show more")
                                            }
                                        },
                                        lineHeight = 22.sp,
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        color = colorResource(id = R.color.DarkblueGrey)
                                    )
                                } else {
                                    Text(
                                        text = info.about,
                                        lineHeight = 22.sp,
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        color = colorResource(id = R.color.DarkblueGrey)
                                    )
                                }

                            }
                            CalendarApp()
                            Spacer(modifier = Modifier.height(50.dp))
                        }

                    }
                }
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.darkBlue),
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(size = 16.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
//                                    .padding(horizontal = 20.dp),

                ) {
                    Text(
                        text = "Book an appointment",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(35.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp),
                            tint = Color.White
                        )
                    }
                }

            }


        }
    }
}

fun filterBooking(bookList : List<bookmarkedDRs>,booked_info : bookmarkedDRs) : List<bookmarkedDRs> {
    val filterList = bookList.filter {
        it.name==booked_info.name&&
        it.job==booked_info.job&&
        it.stars==booked_info.stars&&
        it.reviews==booked_info.reviews&&
        it.exp==booked_info.exp&&
        it.about==booked_info.about&&
        it.img==booked_info.img&&
        it.address==booked_info.address
    }
    return filterList
}




