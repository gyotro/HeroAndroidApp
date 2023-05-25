package com.example.heroapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController,
      //         homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { HomeTopBar({ }) }, content =  {}
    )

}