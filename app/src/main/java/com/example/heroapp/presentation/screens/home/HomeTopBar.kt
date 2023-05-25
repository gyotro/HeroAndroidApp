package com.example.heroapp.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.heroapp.R
import com.example.heroapp.ui.theme.TopAppBarBackgroundColor
import androidx.compose.material.MaterialTheme as MaterialTheme1

@Composable
fun HomeTopBar(onSearchAction: () -> Unit) {
    TopAppBar(
                title = {
                    Text(
                        text = "Explore",
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                },
        backgroundColor = MaterialTheme1.colors.TopAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchAction, enabled = true,
                content = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                }
            )
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar {  }
}
@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HomeTopBarPreviewDark() {
    HomeTopBar {  }
}
