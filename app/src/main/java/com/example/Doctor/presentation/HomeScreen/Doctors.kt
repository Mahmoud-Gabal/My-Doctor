@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.Doctor.presentation.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.Doctor.R
import com.example.Doctor.presentation.NavGraph.Routes
import kotlinx.coroutines.launch

@Composable
fun Oculists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Cardiologists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Psychiatrists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Rheumatologists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Neurologists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Obstetricians(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun O_R_Ls(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Psychologists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Pediatricians(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dermatologistes(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()

    Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                navController.navigate(Routes.HomeScreen.route)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        navigationIconContentColor = Color.Black,
                        containerColor = Color(236,236,236)
                    )

                )
            },
            containerColor = Color(236,236,236)
        ) {
            LazyColumn (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(it)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 10.dp)
            ){
                items(doctors){ doctorInfo ->
                    doctorCard(info = doctorInfo , navController = navController)
                }
            }
        }

}

@Composable
fun Dentists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp).size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun Internists(
    modifier: Modifier = Modifier,
    doctors : List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}

@Composable
fun AllDoctors(
    modifier: Modifier = Modifier,
    doctors: List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}
@Composable
fun AllTopRatedDoctors(
    modifier: Modifier = Modifier,
    doctors: List<DoctorInfo>,
    navController : NavHostController
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Routes.HomeScreen.route)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color(236,236,236)
                )

            )
        },
        containerColor = Color(236,236,236)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ){
            items(doctors){ doctorInfo ->
                doctorCard(info = doctorInfo , navController = navController)
            }
        }
    }
}