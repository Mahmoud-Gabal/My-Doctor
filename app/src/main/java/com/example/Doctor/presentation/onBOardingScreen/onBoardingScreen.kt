package com.example.Doctor.presentation.onBOardingScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Doctor.R
import com.example.Doctor.presentation.onBOardingScreen.Dimens.pageIndicatorWidth
import com.example.Doctor.presentation.onBOardingScreen.components.NewsButton
import com.example.Doctor.presentation.onBOardingScreen.components.NewsTextButton
import com.example.Doctor.presentation.onBOardingScreen.components.pageIndicator
import com.example.Doctor.presentation.onBOardingScreen.pages.welcomePage
import com.example.Doctor.ui.theme.MyDoctorTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onEvent : (onBoardingEvents) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.doc),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .align(Alignment.BottomCenter)
        ){
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
            ) {
                translate(top = 115f){
                    drawArc(
                        color = Color.White,
                        -180f,
                        180f,
                        useCenter = false,

                        )
                }
            }
            Column( modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                val pagerState = rememberPagerState(0) {
                    3
                }
                val scope = rememberCoroutineScope()
                val buttonState = remember{
                    derivedStateOf {
                        when(pagerState.currentPage){
                            0 -> listOf("","Next")
                            1 -> listOf("Back","Next")
                            2 -> listOf("Back","Get started")
                            else -> listOf("","")
                        }
                    }
                }
                HorizontalPager(state = pagerState) {index ->
                   welcomePage()
                }

                pageIndicator(modifier = Modifier.width(pageIndicatorWidth),pageSize = 3, selectedPage = pagerState.currentPage)
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)) {
                    if (buttonState.value[0].isNotEmpty()){
                        NewsTextButton(modifier =  Modifier.height(60.dp),
                            text = buttonState.value[0],
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(page = pagerState.currentPage -1)
                                }
                            }
                        )
                    }
                    NewsButton(modifier = Modifier
                        .weight(1f)
                        .height(60.dp),text = buttonState.value[1]) {
                        scope.launch {
                            if (pagerState.currentPage == 2){
                                onEvent(onBoardingEvents.saveAppEntry)
                            }else{
                                pagerState.animateScrollToPage(page = pagerState.currentPage +1)
                            }
                        }
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoardingPreview() {
    MyDoctorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
//            OnBoardingScreen()
        }
    }
}