package com.example.mydoctor.presentation.onBOardingScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import android.graphics.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.mydoctor.R
import androidx.compose.ui.graphics.colorspace.ColorModel
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.graphics.colorspace.connect
import androidx.compose.ui.unit.dp
import com.example.mydoctor.presentation.onBOardingScreen.Dimens.IndicatorSize
import com.example.mydoctor.presentation.onBOardingScreen.colors.selectedBox
import com.example.mydoctor.presentation.onBOardingScreen.colors.unselectedBox
import com.example.mydoctor.presentation.onBOardingScreen.colors.whiteGrey

@Composable
fun pageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = selectedBox,
    unSelectedColor: Color = whiteGrey
) {
    Row (modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(7.dp))
    {
//        repeat index(page) start from 0
        repeat(pageSize){page ->
            Box(
                modifier = Modifier
                    .width(if (page == selectedPage) IndicatorSize*3 else IndicatorSize)
                    .height(IndicatorSize)
                    .clip( CircleShape)
                    .background(if (page == selectedPage) selectedColor else unSelectedColor)
            )
        }
    }
}