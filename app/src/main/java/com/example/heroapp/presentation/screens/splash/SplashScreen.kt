package com.example.heroapp.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.heroapp.R
import com.example.heroapp.navigation.Screen
import com.example.heroapp.ui.theme.Purple500
import com.example.heroapp.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController, splashViewModel: SplashViewModel = hiltViewModel()) {
    // let's animate the Logo
    val degree = remember {
        Animatable(initialValue = 0f)
    }

    Splash(degree.value)
    // with key = true the block will be executed only once
    // We can Launch the coroutine before or after the screen
    // if we rotate the device, the rotation will be executed again
    LaunchedEffect(key1 = true) {
        degree.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        // si legge il valore dell'onBoarding: se false si va alla welcome screen, altrimenti Home screen
        val onBoarding: Boolean = splashViewModel.onBoardingCompletedState.value
        if (onBoarding) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }else {
            navController.popBackStack()
            navController.navigate(Screen.Welcome.route)
        }
    }
}
@Composable
fun Splash(value: Float) {
    if (isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.rotate(value),
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
                modifier = Modifier.rotate(value),
                painter = painterResource( id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(0f)
}