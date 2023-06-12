package com.example.heroapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.heroapp.presentation.common.ListContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController,
               homeViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.allHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = { HomeTopBar({}) },
        content =
     {
           ListContent(
               heroes = allHeroes, navHostController = navController

           )

    })

}