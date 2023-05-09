package com.example.heroapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.heroapp.R
import com.example.heroapp.ui.theme.Purple40
import com.example.heroapp.ui.theme.Purple500
import com.example.heroapp.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController) {
    Splash()
}
@Composable
fun Splash() {
    if (isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource( id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource( id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash()
}