package com.example.Doctor.presentation.NewsPaperPage

import android.graphics.Paint.Align
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.Doctor.R
import com.example.Doctor.data.remote.Data.Article
import com.example.Doctor.data.remote.Data.ArticleX
import com.example.Doctor.data.remote.Data.SourceName
import com.example.Doctor.presentation.HomeScreen.savedDoctorCard
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.presentation.ViewModels.NewsViewModel

@Preview(showBackground = true)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    articleX: ArticleX = ArticleX("farid diab","","","2024-22-22-34567:45.38z",
        SourceName(null,"Fox News"),"fdsdfs fdsfsf fs  gdgfsfsf  g fdg fsfsfsfsfs  g gdsfgsdfsfsfsfsf  g d gds f sdfsarghthrhbss  gt h b gre r r fr fsfsfsdfsfsdadaad ","fdssd","fdsff"
    ),
    navController : NavHostController = rememberNavController()
) {
    if (articleX.title == "[Removed]"){
        return
    }
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(23.dp))
            .background(Color.White)
            .clickable {
            }
        ,
//        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        if (articleX.urlToImage==null){
            Box(modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(23.dp))
                .background(Color(0xFFB8B5B5))
            ){
                Text(
                    text = articleX.source.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.darkBlue),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }else{
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(articleX.urlToImage)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(23.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                error = {
                    Icon(painter = painterResource(id = R.drawable.warning), contentDescription = null)
                },
                loading = {
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(23.dp))
                            .background(Color(0xFFB8B5B5))
                    )
                }
            )
        }
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(end = 12.dp, top = 5.dp, bottom = 5.dp)) {
            Text(
                text = if(articleX.title.length > 80) articleX.title.substring(0,80)+"...." else articleX.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.darkBlue)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = articleX.source.name,fontSize = 10.sp, modifier = Modifier.weight(1.5f),lineHeight = 12.sp)
                Text(
                    text = articleX.publishedAt.substringBefore("T"),
                    fontSize = 10.sp,
                    modifier = Modifier.weight(1f),
                    lineHeight = 12.sp,
                    textAlign = TextAlign.End
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsPapaerPage(
    modifier: Modifier = Modifier,
    article: Article,
    navController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize()){
        Text(
            text = "Todays' News",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.darkBlue)
        )
        Text(
            text = "${article.totalResults} Results found : ",
            modifier = Modifier.padding(vertical = 3.dp)
        )
        val newsViewModel : NewsViewModel = hiltViewModel()
        val isRefreshing = newsViewModel.isRefreshing.collectAsState()
        val refreshState = rememberPullRefreshState(refreshing = isRefreshing.value, onRefresh = { newsViewModel.refresh() })
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .pullRefresh(refreshState)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = 10.dp, bottom = 70.dp)
            ) {
                items(article.articles) { articlex ->
                    NewsCard(
                        articleX = articlex,
                        navController = navController,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing.value,
                state = refreshState,
                modifier = Modifier.align(
                    Alignment.TopCenter)
            )

        }
    }

}

@Composable
fun shimmerCard(modifier: Modifier = Modifier) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(23.dp))
            .background(Color.White)
            .clickable {
            }
        ,
//        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(23.dp))
                .shimmerEffect()
        )
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(end = 12.dp, top = 5.dp, bottom = 5.dp)) {
            Column{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .shimmerEffect()
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(modifier = Modifier
                    .fillMaxWidth(.6f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect())
                Box(modifier = Modifier
                    .fillMaxWidth(.9f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect())
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun shimmerPage(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ){
        Text(
            text = "Todays' News",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.darkBlue)
        )
        val newsViewModel : NewsViewModel = hiltViewModel()
        val isRefreshing = newsViewModel.isRefreshing.collectAsState()
        val refreshState = rememberPullRefreshState(refreshing = isRefreshing.value, onRefresh = { newsViewModel.refresh() })
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .pullRefresh(refreshState)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 10.dp, bottom = 70.dp)
            ) {
                items(10) { shimmerCard ->
                    shimmerCard()
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing.value,
                state = refreshState,
                modifier = Modifier.align(
                    Alignment.TopCenter)
            )
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun errorPage(
    modifier: Modifier = Modifier,
) {

    Column(modifier = Modifier.fillMaxSize()){
        Text(
            text = "Todays' News",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.darkBlue)
        )
        val newsViewModel : NewsViewModel = hiltViewModel()
        val isRefreshing = newsViewModel.isRefreshing.collectAsState()
        val refreshState = rememberPullRefreshState(refreshing = isRefreshing.value, onRefresh = { newsViewModel.refresh() })
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .pullRefresh(refreshState)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = null,
                )
                Text(text = "Error")
            }

            PullRefreshIndicator(
                refreshing = isRefreshing.value,
                state = refreshState,
                modifier = Modifier.align(
                    Alignment.TopCenter)
            )
        }

    }
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}