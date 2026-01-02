package com.mohamed.kedra.listingdemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mohamed.kedra.listingdemo.presentation.features.details.DetailsScreen
import com.mohamed.kedra.listingdemo.presentation.features.home.HomeScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {

        composable(route = Screens.HomeScreen.route) {
            HomeScreen { id ->
                navController.navigate(Screens.DetailsScreen.createRoute(id))
            }
        }

        composable(
            route = Screens.DetailsScreen.route,
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("launchId")
            id?.let {
                DetailsScreen(
                    id = id,
                    onBackClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

