package com.example.heroapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val StarColor = Color(0xffe1e825)

// Creiamo extension Variable di Color:

val Colors.welcomeScreenBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.titleColor: Color
    @Composable
    get() = if (isLight) Color.DarkGray else Color.LightGray

val Colors.descriptionColor: Color
    @Composable
    get() = if (isLight) Color.DarkGray.copy(alpha = 0.5f)
            else Color.LightGray.copy(alpha = 0.5f)

val Colors.activeindicatorColor: Color
    @Composable
    get() =  if (isLight) Purple500 else Purple700

val Colors.inactiveindicatorColor: Color
    @Composable
    get() =  if (isLight) Color.LightGray else Color.DarkGray

val Colors.TopAppBarBackgroundColor: Color
    @Composable
    get() =  if (isLight) Purple500 else Color.Black