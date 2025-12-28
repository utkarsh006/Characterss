package com.example.characters.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.characters.presentation.character_detail.AnimeDetailScreen
import com.example.characters.presentation.character_list.AnimeListScreen
import com.example.characters.presentation.favorites.FavoritesScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AnimeListScreen.route,
        modifier = modifier
    ) {
        composable(route = Screen.AnimeListScreen.route) {
            AnimeListScreen(context, navController)
        }

        composable(route = Screen.FavoritesScreen.route) {
            FavoritesScreen(navController)
        }

        composable(
            route = Screen.AnimeDetailScreen.route,
            arguments = listOf(
                navArgument("animeId") {
                    type = NavType.IntType
                }
            )
        ) {
            AnimeDetailScreen()
        }
    }
}
