package com.example.heroapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
){
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(3.dp))
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. $item",
                color = textColor,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                fontWeight = FontWeight.Light
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OrderedListPreview() {
    OrderedList(
        title = "Family",
        items = listOf("Hero1", "Hero2"),
        textColor = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
    )
}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OrderedListPreviewDark() {
    OrderedList(
        title = "Family",
        items = listOf("Hero1", "Hero2"),
        textColor = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
    )
}