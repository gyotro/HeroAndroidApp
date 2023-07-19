package com.example.heroapp.presentation.common

import android.text.Layout.Alignment
import android.view.animation.AlphaAnimation
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import coil.compose.AsyncImagePainter
import com.example.heroapp.R
import com.example.heroapp.ui.theme.CONNECTION_ERROR
import com.example.heroapp.ui.theme.SMALL_PADDING
import java.net.SocketException
import java.net.SocketTimeoutException

// verrà richiamata quando ci sarà errore di connessione
@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember {
        mutableStateOf(parseErrorMessage(error.toString()))
    }
    val icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    var starAnimation by remember { mutableStateOf(false) }
    // perché non si usa remember?
    val alphaAnimation by animateFloatAsState(
        // il ContentAlpha.disabled sarebbe simile ad un Gray
            targetValue = if (starAnimation) ContentAlpha.disabled else 0f,
            animationSpec = tween(3000)
        )
    // it will be triggered only the first time
    LaunchedEffect(key1 = true) {
        starAnimation = true
    }

    EmptyContent(
        message = message,
        icon = icon,
        alphaAnimation = alphaAnimation
    )

}
@Composable
fun EmptyContent(message: String, icon: Int, alphaAnimation: Float) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Icon(painter = painterResource(icon),
            contentDescription = "connection_error",
            modifier = Modifier.size(CONNECTION_ERROR).alpha(alphaAnimation),
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Text(
            text = message,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            modifier = Modifier.padding(top = SMALL_PADDING).alpha(alphaAnimation))
    }
}

fun parseErrorMessage(message: String): String {
    return when {
        message.contains("Socket") -> { "Server Unavailable!" }
        message.contains("Connection") -> { "Internet Unavailable!" }
        else -> { "Unknown Error!" }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyScreen(){
    //
    EmptyContent(
        message = parseErrorMessage(LoadState.Error(SocketTimeoutException()).toString()),
        icon = R.drawable.ic_network_error,
        // visibilità totale
        alphaAnimation = 1f
    )
}

