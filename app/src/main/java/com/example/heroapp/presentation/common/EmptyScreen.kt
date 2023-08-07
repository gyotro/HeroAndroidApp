package com.example.heroapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.heroapp.R
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.ui.theme.CONNECTION_ERROR
import com.example.heroapp.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.SocketTimeoutException

// verrà richiamata quando ci sarà errore di connessione
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>
) {
    // di default inseriamo ricerca hero
    var message by remember {
        mutableStateOf("Find your favourite Hero!!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.search_hero)
    }
    // se l'errore non è null, valorizziamo tutto con i dati dell'errore
    if (error?.error?.message != null) {
        message = parseErrorMessage(error.error.message!!)
        icon = R.drawable.ic_network_error
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
        alphaAnimation = alphaAnimation,
        heroes = heroes,
        error = error
    )

}

@Composable
fun EmptyContent(
    message: String,
    icon: Int,
    alphaAnimation: Float,
    error: LoadState.Error?,
    heroes: LazyPagingItems<Hero>? = null
) {
    var isrefreshing by remember { mutableStateOf(false) }
    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isrefreshing),
        onRefresh = {
            isrefreshing = true
            heroes?.refresh()
            isrefreshing = false
        } )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // verticalScroll(rememberScrollState()) is mandatory if we want to use swipeRefresh (with LazyColumnm, verticalScroll(rememberScrollState()) is not required)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "connection_error",
                modifier = Modifier
                    .size(CONNECTION_ERROR)
                    .alpha(alphaAnimation),
                tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
            )
            Text(
                text = message,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alphaAnimation)
            )
        }
    }
}

fun parseErrorMessage(message: String): String {
    return when {
        message.contains("Socket") -> {
            "Server Unavailable!"
        }

        message.contains("Connection") -> {
            "Internet Unavailable!"
        }

        else -> {
            "Unknown Error!"
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyScreen() {
    //
    EmptyContent(
        message = parseErrorMessage(LoadState.Error(SocketTimeoutException()).toString()),
        icon = R.drawable.ic_network_error,
        // visibilità totale
        alphaAnimation = 1f,
        error = null
    )
}

