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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.Doctor.R
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.presentation.SignInScreen.GoogleAuth.UserData
import com.example.Doctor.ui.theme.MyDoctorTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userData: UserData?,
    onSignOut: () -> Unit
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
                        NavigationDrawerItem(
                            label = { Text(text = "Oculist/Ophthalmologist") },
                            selected = listofItems[0] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ophthalmology),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[0]

                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Cardiologist") },
                            selected = listofItems[1] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.heart),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[1]

                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Psychiatrist") },
                            selected = listofItems[2] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.psychiatry),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[2]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Rheumatologist") },
                            selected = listofItems[3] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.rheumatology),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[3]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Neurologist") },
                            selected = listofItems[4] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.brain) ,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[4]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Obstetrician/Gynecologist") },
                            selected = listofItems[5] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.maternity),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[5]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Oto.Rhino.Laryngo.logist\n(O.R.L)") },
                            selected = listofItems[6] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ear),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[6]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Psychologist") },
                            selected = listofItems[7] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.psychology),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[7]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Pediatrician") },
                            selected = listofItems[8] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baby),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[8]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Dermatologiste") },
                            selected = listofItems[9] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.dermatology),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[9]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Dentist") },
                            selected = listofItems[10] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.dentist),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[10]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
                        NavigationDrawerItem(
                            label = { Text(text = "Internist") },
                            selected = listofItems[11] == onSelectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.stomach),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[11]
                            },
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
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
                            badge = { Text(text = "50") },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .padding(vertical = 3.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = colorResource(id = R.color.itemColor),
                                unselectedContainerColor = colorResource(id = R.color.unSelecteditemColor)
                            )
                        )
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
                Text(text = "Hello,")
                if(userData?.username != null) {
                    Text(
                        text = userData.username,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }else{
                    Text(
                        text = "simple user",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Preview
@Composable
fun homePreview(modifier: Modifier = Modifier) {
    MyDoctorTheme {
        val drawerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var onSelectedIndex by rememberSaveable() {
            mutableStateOf(0)
        }
        val listofItems = listOf(0, 1, 2)
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Row (modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                        .padding(vertical = 30.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.medicalteam),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                    }
                    NavigationDrawerItem(
                        label = { Text(text = "Home") },
                        selected = listofItems[0] == onSelectedIndex,
                        icon = {
                            Icon(
                                imageVector = if (listofItems[0] != onSelectedIndex) Icons.Default.Home else Icons.Filled.Home,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            onSelectedIndex = listofItems[0]

                        },
                        badge = { Text(text = "50") },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Profile") },
                        selected = listofItems[1] == onSelectedIndex,
                        icon = {
                            Icon(
                                imageVector = if (listofItems[1] != onSelectedIndex) Icons.Default.Person else Icons.Filled.Person,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            onSelectedIndex = listofItems[1]
                        },
                        badge = { Text(text = "50") },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Settings") },
                        selected = listofItems[2] == onSelectedIndex,
                        icon = {
                            Icon(
                                imageVector = if (listofItems[2] != onSelectedIndex) Icons.Default.Settings else Icons.Filled.Settings,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            onSelectedIndex = listofItems[2]
                        },
                        badge = { Text(text = "50") },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            },
            drawerState = drawerstate
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "")},
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerstate.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            navigationIconContentColor = Color.DarkGray
                        ),
                        actions = {
                            Image(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape),
                                painter = painterResource(id = R.drawable.medicalteam),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    )
                }
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                    contentAlignment = Alignment.Center
                )
                {
                        Text(text = "Hello")
                }
            }
        }
    }
}


@Composable
fun navItem
(
    modifier: Modifier = Modifier,
    text : @Composable () -> Unit,
    Badge : @Composable () -> Unit,
    selected : Boolean,
    onClick : () -> Unit,
    myIcon : @Composable () (() -> Unit)? = null
) {
    NavigationDrawerItem(
        label = text,
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        badge = Badge,
        icon = myIcon
    )
}

