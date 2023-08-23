package com.example.heroapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(
    navController: NavHostController,
    // appena clicchiamo sull'immagine per avere il dettaglio, sarà inizializzata la DetailsScreenViewModel
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
)
{

}