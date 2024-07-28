package com.example.Doctor.presentation.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.Doctor.R
import com.example.Doctor.domain.local.db.bookmarkedDRs

import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.presentation.NewsPaperPage.shimmerEffect

@Preview
@Composable
fun doctorCard(
    modifier: Modifier = Modifier,
    info: DoctorInfo = DoctorInfo("mahmoud","doc",3,45,3,"ggg",R.drawable.wd1,"Cairo"),
    navController: NavHostController = rememberNavController()
)
{
    var restStars  by remember {
        mutableStateOf(5 - info.stars)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(23.dp))
            .background(Color.White)
            .clickable {
                navController.navigate(
                    Routes.AboutDoctor.route
                            + "/${info.name}/${info.job}/${info.stars}/${info.reviews}/${info.exp}/${info.about}/${info.img}/${info.address}"
                )
            }
            .padding(12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Image(
            painter = painterResource(id = info.img),
            contentDescription = null,
            modifier = Modifier
                .size(67.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(text = info.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.darkBlue)
            )
            Text(text = info.job, fontSize = 12.sp)
            Row(modifier = Modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically){
                for (i in 1..info.stars) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(12.dp)
                    )
                }
                for (i in 1..restStars) {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                }
                Text(text = "   ${info.stars}.0" +  "    |    " +"${info.reviews} Reviews",fontSize = 12.sp)
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = null,
            tint = colorResource(id = R.color.splashBackgroundTr),
            modifier = Modifier
                .padding(start = 23.dp)
                .size(30.dp)
        )
    }
}

@Composable
fun savedDoctorCard(
    modifier: Modifier = Modifier,
    info: bookmarkedDRs = bookmarkedDRs("mahmoud","doc",3,45,3,"ggg",R.drawable.wd1,"Cairo"),
    navController: NavHostController = rememberNavController()
)
{
    var restStars  by remember {
        mutableStateOf(5 - info.stars)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(23.dp))
            .background(Color.White)
            .clickable {
                navController.navigate(
                    Routes.AboutDoctor.route
                            + "/${info.name}/${info.job}/${info.stars}/${info.reviews}/${info.exp}/${info.about}/${info.img}/${info.address}"
                )
            }
            .padding(12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Image(
            painter = painterResource(id = info.img),
            contentDescription = null,
            modifier = Modifier
                .size(67.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(text = info.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.darkBlue)
            )
            Text(text = info.job, fontSize = 12.sp)
            Row(modifier = Modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically){
                for (i in 1..info.stars) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(12.dp)
                    )
                }
                for (i in 1..restStars) {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                }
                Text(text = "   ${info.stars}.0" +  "    |    " +"${info.reviews} Reviews",fontSize = 12.sp)
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = null,
            tint = colorResource(id = R.color.splashBackgroundTr),
            modifier = Modifier
                .padding(start = 0.dp)
                .size(30.dp)
        )
    }
}

@Composable
fun shimmerDoctorCard(
    modifier: Modifier = Modifier,

)
{
    Row (
        modifier = Modifier
            .fillMaxWidth(.9f)
            .clip(RoundedCornerShape(23.dp))
            .background(Color.White)
            .padding(12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Box(modifier = Modifier
            .size(67.dp)
            .clip(RoundedCornerShape(15.dp))
            .shimmerEffect()
        )
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(.3f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.15f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect()
            )
        }
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
    }
}