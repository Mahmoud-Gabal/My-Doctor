package com.example.Doctor.presentation.onBOardingScreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.Doctor.R
import com.example.Doctor.presentation.onBOardingScreen.colors.whiteGrey

@Composable
fun NewsButton(
    modifier: Modifier = Modifier,
    text  :String,
    onClick : () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.darkBlue),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 16.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun NewsTextButton(
    modifier: Modifier = Modifier,
    text  :String,
    onClick : () -> Unit
) {
    TextButton(onClick = onClick,modifier = modifier,shape = RoundedCornerShape(size = 16.dp)) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = whiteGrey
        )
    }
}