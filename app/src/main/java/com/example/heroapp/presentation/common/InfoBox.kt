package com.example.heroapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp


@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
    ) {
    Row {
       Icon(painter = icon, contentDescription = "Info Icon", tint = iconColor)
        Column {
            Text(text = bigText, color = textColor, fontSize = MaterialTheme.typography.displayMedium.fontSize, fontWeight = FontWeight.Bold)
            Text(modifier = Modifier.alpha(ContentAlpha.medium), text = smallText, color = textColor, fontSize = MaterialTheme.typography.displaySmall.fontSize, fontWeight = FontWeight.Light)
        }
    }
}