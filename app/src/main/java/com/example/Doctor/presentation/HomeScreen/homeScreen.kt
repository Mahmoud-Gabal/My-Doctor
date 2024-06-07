package com.example.Doctor.presentation.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.Doctor.R
import com.example.Doctor.data.local.doctors.CardiologistsList
import com.example.Doctor.data.local.doctors.DentistsList
import com.example.Doctor.data.local.doctors.DermatologistesList
import com.example.Doctor.data.local.doctors.InternistsList
import com.example.Doctor.data.local.doctors.NeurologistsList
import com.example.Doctor.data.local.doctors.O_R_LsList
import com.example.Doctor.data.local.doctors.ObstetriciansList
import com.example.Doctor.data.local.doctors.OculistsList
import com.example.Doctor.data.local.doctors.PediatriciansList
import com.example.Doctor.data.local.doctors.PsychologistsList
import com.example.Doctor.data.local.doctors.RheumatologistsList
import com.example.Doctor.domain.remote.NewsRepo
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.presentation.NewsPaperPage.NewsPapaerPage
import com.example.Doctor.presentation.NewsPaperPage.errorPage
import com.example.Doctor.presentation.NewsPaperPage.shimmerPage
import com.example.Doctor.presentation.SavedDoctorPage.savedDoctorsPage
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.UserData
import com.example.Doctor.presentation.ViewModels.BookViewModel
import com.example.Doctor.presentation.ViewModels.MainViewModel
import com.example.Doctor.presentation.ViewModels.NewsViewModel
import com.example.Doctor.presentation.ViewModels.newsEvents
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@ExperimentalComposeUiApi
@Composable
fun homeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    userData: UserData? = null,
    onSignOut: () -> Unit = {}

) {

    val drawerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
//    val gestureState by remember {
//        derivedStateOf{
//            if(drawerstate.isOpen) true  else false
//        }
//    }
    ModalNavigationDrawer(
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet (
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .padding(bottom = 60.dp),
//                windowInsets = WindowInsets(bottom = 0, top = 0),
                  drawerContainerColor = Color(236, 236, 236)
            ){
                Column(
                    modifier = Modifier
                        .background(Color(236, 236, 236))

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
//                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .padding(vertical = 6.dp, horizontal = 6.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(vertical = 22.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (userData?.profilePictureUrl != null) {
                            AsyncImage(
                                model = userData.profilePictureUrl,
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.Gray)
                            )
                        }
                        if (userData?.username != null) {
                            Text(
                                text = userData.username,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        } else {
                            Text(
                                text = "simple User",
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                    LazyColumn {
                        item {
                            allNavItems(onSignOut = { onSignOut() }, navController = navController)
                        }
                    }
                }
            }
        },
        drawerState = drawerstate
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerstate.open()
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu),
                                contentDescription = null,

                                )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        navigationIconContentColor = Color.Black,
                        containerColor = Color(236, 236, 236)
                    ),
                    actions = {
                        Row {
                            if (userData?.profilePictureUrl != null) {
                                AsyncImage(
                                    model = userData.profilePictureUrl,
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp)
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(15.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 20.dp)
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color.Gray)

                                )
                            }
                        }
                    },

                    )
            },
            containerColor = Color(236, 236, 236),

            ) {
            basicHomeScreen(
                paddingValues = it,
                userData = userData,
                navController = navController
            )
        }


    }

}

//Row(
//modifier = Modifier.padding(horizontal = 20.dp)
//.height(60.dp)
//.align(Alignment.BottomCenter)
//.clip(RoundedCornerShape(16.dp))
//.background(colorResource(id = R.color.darkBlue)),
//verticalAlignment = Alignment.CenterVertically,
//) {
//    var selectedIndex by rememberSaveable {
//        mutableStateOf(1)
//    }
//    var navList = (1..5).toList()
//    var filled = listOf(
//        Icons.Filled.Home,
//        Icons.Filled.Message,
//        Icons.Filled.CalendarMonth,
//        Icons.Filled.Bookmark,
//        Icons.Filled.Newspaper,
//    )
//    var outlined = listOf(
//        Icons.Outlined.Home,
//        Icons.Outlined.Message,
//        Icons.Outlined.CalendarMonth,
//        Icons.Outlined.BookmarkBorder,
//        Icons.Outlined.Newspaper,
//    )
//    navList.forEachIndexed { index, i ->
//        FloatingActionButton(
//            onClick = { selectedIndex = navList[index] },
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxHeight(),
//            containerColor = Color.Transparent,
//        ) {
//            if (navList[index] == selectedIndex) {
//                Icon(
//                    tint = Color.White,
//                    imageVector = filled[index],
//                    contentDescription = null,
//                    modifier = Modifier
//                        .customShadow(
//                            color = Color(255, 255, 255, alpha = 0x53),
//                            borderRadius = 12.dp,
//                            spread = 50.dp,
//                            blurRadius = 0.dp,
//                            offsetY = 0.dp
//                        )
//                        .shadow(10.dp, spotColor = Color.White,)
//
//                )
//            } else {
//                Icon(
//                    tint = colorResource(id = R.color.DarkblueGrey),
//                    imageVector = outlined[index],
//                    contentDescription = null,
//                )
//            }
//
//        }
//    }
//}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun basicHomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    userData: UserData? = null,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
    ) {

        val pagerState = rememberPagerState (0){
            5
        }
        val scope = rememberCoroutineScope()
        HorizontalPager(state = pagerState) { index ->
            when (index) {
                0 -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
//                .padding(paddingValues)
//                .padding(horizontal = 20.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(bottom = 15.dp)
                        ) {
                            Text(text = "Hello,")
                            if (userData?.username != null) {
                                Text(
                                    text = userData.username,
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.darkBlue)
                                )
                            } else {
                                Text(
                                    text = "simple user",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.darkBlue)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight(.32f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(23.dp)),
                            contentAlignment = Alignment.CenterStart,

                            ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(23.dp))
                                    .background(colorResource(id = R.color.splashBackgroundTr))
                                    .padding(20.dp),
                                contentAlignment = Alignment.CenterStart,

                                ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    dropDownMenu()
                                    Column {
                                        var searchText by remember {
                                            mutableStateOf("")
                                        }
                                        TextField(
                                            value = searchText,
                                            onValueChange = {
                                                searchText = it
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(50.dp)
                                                .clip(RoundedCornerShape(20.dp))
//                                ,
//                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
//                                keyboardActions = KeyboardActions(onSearch = {
//                                    focusManager.clearFocus()
//                                })
                                            , placeholder = {
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

                                    }

                                }

                            }
                            Text(
                                text = "Find the doctor nearest to your\nlocation",
                                fontSize = 21.sp,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                        categoryPager(navController = navController)

                    }

                }

                1 -> {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Messages")
                    }
                }

                2 -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "calender")
                    }
                }

                3 -> {
                    savedDoctorsPage(
                        navController = navController
                    )
                }

                4 -> {
                    val newsModel : NewsViewModel = hiltViewModel()
                    val uiState = newsModel.uiState
                    when(uiState){
                        newsEvents.Error -> {
                            errorPage()
                        }
                        newsEvents.loading -> shimmerPage()
                        is newsEvents.success -> NewsPapaerPage(article = uiState.article, navController = navController)
                    }
                }
            }

        }
        Row(
                modifier = Modifier
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.darkBlue)),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        var navList = (0..4).toList()
        var filled = listOf(
            Icons.Filled.Home,
            Icons.Filled.Message,
            Icons.Filled.CalendarMonth,
            Icons.Filled.Bookmark,
            Icons.Filled.Newspaper,
        )
        var outlined = listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Message,
            Icons.Outlined.CalendarMonth,
            Icons.Outlined.BookmarkBorder,
            Icons.Outlined.Newspaper,
        )
        navList.forEachIndexed { index, i ->
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(page = index)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                containerColor = Color.Transparent,
            ) {
                if (pagerState.currentPage == index) {
                    Icon(
                        tint = Color.White,
                        imageVector = filled[index],
                        contentDescription = null,
                        modifier = Modifier
                            .customShadow(
                                color = Color(255, 255, 255, alpha = 0x53),
                                borderRadius = 12.dp,
                                spread = 50.dp,
                                blurRadius = 0.dp,
                                offsetY = 0.dp
                            )
                            .shadow(10.dp, spotColor = Color.White)

                    )
                } else {
                    Icon(
                        tint = colorResource(id = R.color.DarkblueGrey),
                        imageVector = outlined[index],
                        contentDescription = null,
                    )
                }

            }
        }
    }

    }
}

@Composable
fun navItem(
    label: String,
    selected: Boolean,
    onclick: () -> Unit,
    badge: String,
    picId: Int
) {
    NavigationDrawerItem(
        label = { Text(text = label) },
        selected = selected,
        onClick = { onclick() },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.White,
            unselectedContainerColor = Color.White
        ),
        badge = { Text(text = badge) },
        icon = {
            Icon(
                painter = painterResource(id = picId),
                contentDescription = null
            )
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
            .padding(vertical = 3.dp)
            .border(1.dp, Color.LightGray, CircleShape)
    )
}

@Composable
fun allNavItems(
    modifier: Modifier = Modifier,
    onSignOut: () -> Unit,
    navController: NavHostController
) {
    var onSelectedIndex by rememberSaveable() {
        mutableStateOf(0)
    }
    val listofItems = (1..13).toList()
    val picIds = listOf(
        R.drawable.ophthalmology, R.drawable.heart, R.drawable.psychiatry,
        R.drawable.rheumatology, R.drawable.brain, R.drawable.maternity,
        R.drawable.ear, R.drawable.psychology, R.drawable.baby,
        R.drawable.dermatology, R.drawable.dentist, R.drawable.stomach
    )
    val badges = listOf(
        "50", "60", "67", "23", "55", "78", "98", "12", "32", "44", "25", "47"
    )
    val jobs = listOf(
        "Oculist/Ophthalmologist", "Cardiologist", "Psychiatrist",
        "Rheumatologist", "Neurologist", "Obstetrician/Gynecologist",
        "Oto.Rhino.Laryngo.logist\n(O.R.L)", "Psychologist", "Pediatrician",
        "Dermatologiste", "Dentist", "Internist"
    )
    for (i in 0..11) {
        navItem(
            label = jobs[i],
            selected = listofItems[i] == onSelectedIndex,
            onclick = {
                onSelectedIndex = listofItems[i]
                categoryNavigator(navController = navController, i = i)
            },
            badge = badges[i],
            picId = picIds[i]
        )
    }
    NavigationDrawerItem(
        label = { Text(text = "Log out") },
        selected = listofItems[12] == onSelectedIndex,
        icon = {
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = null
            )
        },
        onClick = {
            onSelectedIndex = listofItems[12]
            onSignOut()
            navController.navigate(Routes.signIn.route)
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
            .padding(vertical = 3.dp)
            .border(1.dp, Color.LightGray, CircleShape),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.White,
            unselectedContainerColor = Color.White
        )
    )
}


@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf(
        "Cairo",
        "Alex",
        "Damiette",
        "Luxor",
        "Aswan",
        "Dakahlia",
        "North Sinai",
        "Fayoum",
        "Port Said",
        "Gharbia",
        "Giza",
        "Qalyubia",
        "Sharqia",
        "Ismailia",
        "Suez",
        "Kafr El Sheikh",
        "Gharbia",
        "Menoufia",
        "Beheira",
        "Matrouh",
        "South Sinai",
        "Beni Suef",
        "Minya",
        "Assiut",
        "Sohag",
        "Qena",
        "Red Sea",
        "El Wadi El Gedid"
    )
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column() {
        TextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            placeholder = {
                Text("Select location", color = Color.White)
            },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    },
                    tint = Color.White
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.locationBar),
                errorContainerColor = colorResource(id = R.color.locationBar),
                focusedContainerColor = colorResource(id = R.color.locationBar),
                errorTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                errorCursorColor = Color.White,
            )

        )
        DropdownMenuNoPaddingVeitical(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .height(250.dp),

            ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = label
                        expanded = false
                    },
                    text = { Text(text = label) },
                    modifier = Modifier.height(40.dp)
                )
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }
        }
    }

}

@Composable
fun categoryPager(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.darkBlue)
            )
            Text(
                text = "See all",
                modifier = Modifier.clickable { navController.navigate(Routes.AllDoctorsScreen.route) })
        }
        categories(
            navController = navController,
        )
    }

}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun categories(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val categoryList = listOf(
        "Oculist/Ophthalmologist", "Cardiologist", "Psychiatrist",
        "Rheumatologist", "Neurologist", "Obstetrician/Gynecologist",
        "Oto.Rhino.Laryngo.logist\n(O.R.L)", "Psychologist", "Pediatrician",
        "Dermatologiste", "Dentist", "Internist"
    )
    val iconsOfJobs = listOf(
        R.drawable.ophthalmology, R.drawable.heart, R.drawable.psychiatry,
        R.drawable.rheumatology, R.drawable.brain, R.drawable.maternity,
        R.drawable.ear, R.drawable.psychology, R.drawable.baby,
        R.drawable.dermatology, R.drawable.dentist, R.drawable.stomach
    )

    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    var toprated by rememberSaveable {
        mutableStateOf(getTopRated(OculistsList))
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(categoryList) { index, item ->
            var isSelected = index == selectedIndex
            val selectedColor =
                if (isSelected) Color.White else Color.Transparent
            Column(
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(18.dp))
                    .background(selectedColor)
                    .clickable {

                        selectedIndex = index

                        when (index) {
                            0 -> toprated = getTopRated(OculistsList)
                            1 -> toprated = getTopRated(CardiologistsList)
                            2 -> toprated = getTopRated(PsychologistsList)
                            3 -> toprated = getTopRated(RheumatologistsList)
                            4 -> toprated = getTopRated(NeurologistsList)
                            5 -> toprated = getTopRated(ObstetriciansList)
                            6 -> toprated = getTopRated(O_R_LsList)
                            7 -> toprated = getTopRated(PsychologistsList)
                            8 -> toprated = getTopRated(PediatriciansList)
                            9 -> toprated = getTopRated(DermatologistesList)
                            10 -> toprated = getTopRated(DentistsList)
                            11 -> toprated = getTopRated(InternistsList)
                        }

                    }

            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        painter = painterResource(iconsOfJobs[index]),
                        contentDescription = null
                    )
                    Text(
                        text = item,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.darkBlue)
                    )
                }
            }
        }


    }
    topRated(navController = navController, toprated = toprated)
}


@Composable
fun topRated(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    toprated: MutableList<DoctorInfo>
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Top Rated Doctors",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.darkBlue)
            )
            Text(
                text = "See all",
                modifier = Modifier.clickable { navController.navigate(Routes.AllTopRatedDoctorsScreen.route) })
        }
        topDoctors(
            topRated = toprated, navController = navController
        )

    }
}

fun categoryNavigator(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    i: Int
) {
    when (i) {
        0 -> navController.navigate(Routes.OculistsScreen.route)
        1 -> navController.navigate(Routes.CardiologistsScreen.route)
        2 -> navController.navigate(Routes.PsychiatristsScreen.route)
        3 -> navController.navigate(Routes.RheumatologistsScreen.route)
        4 -> navController.navigate(Routes.NeurologistsScreen.route)
        5 -> navController.navigate(Routes.ObstetriciansScreen.route)
        6 -> navController.navigate(Routes.O_R_LsScreen.route)
        7 -> navController.navigate(Routes.PsychologistsScreen.route)
        8 -> navController.navigate(Routes.PediatriciansScreen.route)
        9 -> navController.navigate(Routes.DermatologistesScreen.route)
        10 -> navController.navigate(Routes.DentistsScreen.route)
        11 -> navController.navigate(Routes.InternistsScreen.route)
    }
}



