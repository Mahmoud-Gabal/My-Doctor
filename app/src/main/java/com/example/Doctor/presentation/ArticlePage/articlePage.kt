package com.example.Doctor.presentation.ArticlePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.Doctor.R
import com.example.Doctor.data.remote.Data.ArticleX
import com.example.Doctor.data.remote.Data.SourceName
import com.example.Doctor.presentation.HomeScreen.filterBooking
import com.example.Doctor.presentation.NavGraph.HyperlinkText
import com.example.Doctor.presentation.ViewModels.BookViewModel
import com.example.Doctor.presentation.ViewModels.BookingEvents
import com.example.Doctor.presentation.ViewModels.sharedDataViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun articlePage(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    sharedDataViewModel: sharedDataViewModel = viewModel()
) {
    var articleX = sharedDataViewModel.articlex
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
//                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
//                                .size(35.dp)
                            ,
                            tint = colorResource(id = R.color.darkBlue)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    containerColor = Color.Transparent
                ),

            )
        },
        containerColor = Color.White

    ) {
        Box(modifier = modifier
            .fillMaxSize()
            .padding(it)){
            articleX?.let{
                if (it.urlToImage == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
//                .clip(RoundedCornerShape(23.dp))
                            .background(Color(0xFFB8B5B5))
                    ) {
                        Text(
                            text = it.source?.name!!,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.darkBlue),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(bottom = 70.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.urlToImage)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
//                    .clip(RoundedCornerShape(23.dp))
                        ,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        error = {
                            Icon(
                                painter = painterResource(id = R.drawable.warning),
                                contentDescription = null
                            )
                        },
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(.5f)
                                    .background(Color(0xFFB8B5B5))
                            )
                        }
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.6f)
                        .clip(RoundedCornerShape(topEnd = 27.dp, topStart = 27.dp))
                        .background(Color.White)
                        .align(Alignment.BottomCenter)
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    contentPadding = PaddingValues(vertical = 25.dp)
                ) {
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            it.source?.let{
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.darkBlue)
                                        )
                                    ) {
                                        append("Source : ")
                                    }
                                    append(it.name)
                                }
                                )
                            }
                            it.publishedAt?.let{
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.darkBlue)
                                        )
                                    ) {
                                        append("Published at : ")
                                    }
                                    append(it)
                                }
                                )
                            }
                            it.author?.let{
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.darkBlue)
                                        )
                                    ) {
                                        append("Author : ")
                                    }
                                    append(it)
                                }
                                )
                            }
                            it.description?.let{
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.darkBlue)
                                        )
                                    ) {
                                        append("Description : ")
                                    }
                                    append(it)
                                }
                                )
                            }
                            it.content?.let{
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.darkBlue)
                                        )
                                    ) {
                                        append("Content :- ")
                                    }
                                }
                                )
                            }
                            it.title?.let{
                                Text(
                                    text = it,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.darkBlue),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            it.content?.let{
                                Text(
                                    text = it,
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                            it.url?.let{
                                HyperlinkText(
                                    fullText = "Resources : " + it,
                                    hyperLinks = mutableMapOf(
                                        it to it
                                    ),
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.darkBlue)
                                    )
                                )
                            }

                        }
                    }
                }
            }
        }
    }


}