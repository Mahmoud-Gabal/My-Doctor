package com.example.Doctor.presentation.ArticlePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.Doctor.R
import com.example.Doctor.data.remote.Data.ArticleX
import com.example.Doctor.data.remote.Data.SourceName
import com.example.Doctor.presentation.NavGraph.HyperlinkText


@Preview
@Composable
fun articlePage(
    modifier: Modifier = Modifier,
    articleX: ArticleX = ArticleX("farid diab","this is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the descthis is the desc","this is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the contentthis is the content","2024-22-22-34567:45.38z",
        SourceName(null,"Fox News"),"this is the title", url = "this is the url", urlToImage = null)
) {
    Box(modifier = modifier.fillMaxSize()){
        if (articleX.urlToImage==null){
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
//                .clip(RoundedCornerShape(23.dp))
                .background(Color(0xFFB8B5B5))
            ){
                Text(
                    text = articleX.source.name,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.darkBlue),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 70.dp),
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
                    .fillMaxWidth()
                    .fillMaxHeight(.4f)
//                    .clip(RoundedCornerShape(23.dp))
                ,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                error = {
                    Icon(painter = painterResource(id = R.drawable.warning), contentDescription = null)
                },
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.4f)
                            .background(Color(0xFFB8B5B5))
                    )
                }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
                .clip(RoundedCornerShape(topEnd = 27.dp, topStart = 27.dp))
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
             item {
                 Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                     Text(text = buildAnnotatedString {
                         withStyle(
                             SpanStyle(
                                 fontWeight = FontWeight.SemiBold,
                                 color = colorResource(id = R.color.darkBlue)
                             )
                         ) {
                             append("Source : ")
                         }
                         append(articleX.source.name)
                     }
                     )
                     Text(text = buildAnnotatedString {
                         withStyle(
                             SpanStyle(
                                 fontWeight = FontWeight.SemiBold,
                                 color = colorResource(id = R.color.darkBlue)
                             )
                         ) {
                             append("Published at : ")
                         }
                         append(articleX.publishedAt)
                     }
                     )
                     Text(text = buildAnnotatedString {
                         withStyle(SpanStyle(
                             fontWeight = FontWeight.SemiBold,
                             color = colorResource(id = R.color.darkBlue)
                         )
                         ){
                             append("Author : ")
                         }
                         append(articleX.author)
                     }
                     )
                     Text(text = buildAnnotatedString {
                         withStyle(SpanStyle(
                             fontWeight = FontWeight.SemiBold,
                             color = colorResource(id = R.color.darkBlue)
                         )
                         ){
                             append("Description : ")
                         }
                         append(articleX.description)
                     }
                     )
                     Text(text = buildAnnotatedString {
                         withStyle(SpanStyle(
                             fontWeight = FontWeight.SemiBold,
                             color = colorResource(id = R.color.darkBlue)
                         )
                         ){
                             append("Content :- ")
                         }
                     }
                     )
                     Text(
                         text = articleX.title,
                         fontSize = 28.sp,
                         fontWeight = FontWeight.SemiBold,
                         color = colorResource(id = R.color.darkBlue),
                         modifier = Modifier.fillMaxWidth(),
                         textAlign = TextAlign.Center
                     )
                     Text(
                         text = articleX.content,
//                         fontSize = 28.sp,
//                         fontWeight = FontWeight.SemiBold,
//                         color = colorResource(id = R.color.darkBlue),
                         modifier = Modifier.fillMaxWidth(),
                     )
                     HyperlinkText(
                         fullText = "Resources : " + articleX.url,
                         hyperLinks = mutableMapOf(
                             articleX.url to articleX.url
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