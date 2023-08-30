package com.example.satnyc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.satnyc.Pages.DETAIL_SCREEN
import com.example.satnyc.Pages.HOME_SCREEN
import com.example.satnyc.viewmodel.SchoolViewModel
import com.example.satnyc.ui.screens.DetailScreen
import com.example.satnyc.ui.screens.HomeScreen


sealed class Screens(val route: String) {
    object Home : Screens(route = HOME_SCREEN)
    object Detail : Screens(route = DETAIL_SCREEN)
}

object Pages {
    const val HOME_SCREEN = "home_screen"
    const val DETAIL_SCREEN = "detail_screen"
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: SchoolViewModel) {

    NavHost(navController = navController, startDestination = Screens.Home.route) {

        composable(route = Screens.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }

        composable(route = Screens.Detail.route + "/{dbn}") { backStackEntry ->
            DetailScreen(
                dbn = backStackEntry.arguments?.getString("dbn") ?: "1",
                viewModel = viewModel,
                navController = navController
            )
        }

    }

}