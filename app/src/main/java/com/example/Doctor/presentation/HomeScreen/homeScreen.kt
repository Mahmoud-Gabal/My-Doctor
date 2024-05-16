package com.example.Doctor.presentation.HomeScreen

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.Doctor.R
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.UserData
import com.example.Doctor.ui.theme.MyDoctorTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlin.math.truncate

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
    var onSelectedIndex by rememberSaveable() {
        mutableStateOf(0)
    }
    val listofItems = (1..13).toList()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Row (modifier = Modifier
                    .fillMaxWidth()
//                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                    .padding(vertical = 6.dp, horizontal = 6.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.blueGrey))
                    .padding(vertical = 22.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if(userData?.profilePictureUrl != null) {
                        AsyncImage(
                            model = userData.profilePictureUrl,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }else{
                        Box(modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Gray))
                    }
                    if(userData?.username != null) {
                        Text(
                            text = userData.username,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }else{
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
                        allNavItems(onSignOut = {onSignOut()}, navController = navController)
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
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        navigationIconContentColor = Color.Black
                    ),
                    actions = {
                        if(userData?.profilePictureUrl != null) {
                            AsyncImage(
                                model = userData.profilePictureUrl,
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(15.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }else{
                            Box(modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color.Gray))
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ){
                Column(
                    modifier = Modifier.padding(bottom = 15.dp)
                ){
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
                        .clip(RoundedCornerShape(23.dp))
                    ,
                    contentAlignment = Alignment.CenterStart,

                ){
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(23.dp))
                        .background(colorResource(id = R.color.splashBackgroundTr))
                        .padding(20.dp)
                    ,
                    contentAlignment = Alignment.CenterStart,

                    ){
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        dropDownMenu()
                        var SearchText by rememberSaveable { mutableStateOf("") }
                        TextField(
                            value = SearchText,
                            onValueChange = { SearchText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            placeholder = {
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
                    Text(
                        text = "Find the doctor nearest to your\nlocation",
                        fontSize = 21.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
                categoryPager()

            }
        }
    }

}

@Composable
fun navItem
(
    label : String,
    selected  :Boolean,
    onclick : () -> Unit,
    badge  :String,
    picId : Int
) {
    NavigationDrawerItem(
        label = { Text(text = label) },
        selected = selected,
        onClick = { onclick() },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorResource(id = R.color.itemColor),
            unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
        ),
        badge = { Text(text = badge)},
        icon = {
           Icon(
               painter = painterResource(id = picId),
               contentDescription = null
           )
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
            .padding(vertical = 3.dp)
    )
}

@Composable
fun allNavItems(
    modifier: Modifier = Modifier,
    onSignOut: () -> Unit,
    navController: NavHostController
)
  {
    var onSelectedIndex by rememberSaveable() {
        mutableStateOf(0)
    }
    val listofItems = (1..13).toList()
    val picIds = listOf(
        R.drawable.ophthalmology,R.drawable.heart,R.drawable.psychiatry,
        R.drawable.rheumatology,R.drawable.brain,R.drawable.maternity,
        R.drawable.ear,R.drawable.psychology,R.drawable.baby,
        R.drawable.dermatology,R.drawable.dentist,R.drawable.stomach
    )
    val badges = listOf(
        "50","60","67","23","55","78","98","12","32","44","25","47"
    )
    val jobs = listOf(
        "Oculist/Ophthalmologist","Cardiologist","Psychiatrist",
        "Rheumatologist","Neurologist","Obstetrician/Gynecologist",
        "Oto.Rhino.Laryngo.logist\n(O.R.L)","Psychologist","Pediatrician",
        "Dermatologiste","Dentist","Internist"
    )
    for (i in 0..11){
        navItem(
            label = jobs[i],
            selected = listofItems[i] == onSelectedIndex,
            onclick = { onSelectedIndex = listofItems[i] },
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
            .padding(vertical = 3.dp),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorResource(id = R.color.itemColor),
            unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
        )
    )
}


@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf(
        "Cairo", "Alex", "Damiette", "Luxor","Aswan",
        "Cairo", "Alex", "Damiette", "Luxor","Aswan",
        "Cairo", "Alex", "Damiette", "Luxor","Aswan",
        "Cairo", "Alex", "Damiette", "Luxor","Aswan",
        "Cairo", "Alex", "Damiette", "Luxor","Aswan",
        "Cairo", "Alex", "Damiette", "Luxor","Aswan",
    )
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

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
                Icon(icon,"contentDescription",
                    modifier = Modifier.clickable { expanded = !expanded },
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
            readOnly = false,
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
                    text = { Text(text = label)},
                    modifier = Modifier.height(40.dp)
                )
                Box(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray))
            }
        }
    }

}

@Composable
fun categoryPager(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(top = 15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Category",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.darkBlue)
            )
            Text(text = "See all")
        }
        categories()
    }
}
@Composable
fun categories(modifier: Modifier = Modifier) {
    val categoryList = listOf(
        "Oculist/Ophthalmologist","Cardiologist","Psychiatrist",
        "Rheumatologist","Neurologist","Obstetrician/Gynecologist",
        "Oto.Rhino.Laryngo.logist\n(O.R.L)","Psychologist","Pediatrician",
        "Dermatologiste","Dentist","Internist"
    )
    val iconsOfJobs = listOf(
        R.drawable.ophthalmology,R.drawable.heart,R.drawable.psychiatry,
        R.drawable.rheumatology,R.drawable.brain,R.drawable.maternity,
        R.drawable.ear,R.drawable.psychology,R.drawable.baby,
        R.drawable.dermatology,R.drawable.dentist,R.drawable.stomach
    )

    LazyRow (
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        itemsIndexed(categoryList){ index,item ->
            var isSelected by remember {
                mutableStateOf(false)
            }
            val selectedColor = animateColorAsState(
                targetValue = if (isSelected) Color.Yellow else Color.Transparent)
            Card(
//                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .height(50.dp)
                    .clickable { isSelected = !isSelected }
                ,
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = selectedColor.value
                ),
                border = BorderStroke(1.dp,Color.LightGray)
            ) {
                Row (
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ){
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
}

