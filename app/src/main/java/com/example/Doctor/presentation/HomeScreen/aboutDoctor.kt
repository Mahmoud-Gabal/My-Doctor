package com.example.Doctor.presentation.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.Doctor.R

@Preview(showBackground = true)
@Composable
fun aboutDoctor(
    modifier: Modifier = Modifier,
    info: DoctorInfo = DoctorInfo("","",0,0,0,"",R.drawable.doc)
) {
    Column(modifier = modifier.fillMaxSize()){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.3f)){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.9f)
                    .clip(RoundedCornerShape(bottomStart = 40.dp))
                    .background(color = colorResource(id = R.color.splashBackgroundTr))
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp),
                        tint = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.BottomCenter)
                ,
            ){
                Image(
                    painter = painterResource(id = R.drawable.doc),
                    contentDescription = null,
                    modifier = Modifier
                        .size(67.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}