package com.example.heroapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.heroapp.util.Constants.DETAILS_ARGUMENT_KEY

// in questo file sarà presente il grafo di navigazione dell'App

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {

        }
        composable(route = Screen.Welcome.route) {

        }
        composable(route = Screen.Home.route) {

        }
        composable(
            route = Screen.Details.route,
            // per lo screen Details vanno aggiunti i parametri (in questo caso 1)
            arguments = listOf(navArgument(name = DETAILS_ARGUMENT_KEY,
                builder = {
                type = NavType.IntType
            })
        ) )
        {

        }
        composable(route = Screen.Search.route) {

        }
    }
}