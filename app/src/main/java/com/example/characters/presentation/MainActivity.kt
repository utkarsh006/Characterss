package com.example.characters.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.characters.navigation.BottomNavigationBar
import com.example.characters.navigation.Screen
import com.example.characters.presentation.character_detail.AnimeDetailScreen
import com.example.characters.presentation.character_list.AnimeListScreen
import com.example.characters.presentation.character_list.AnimeListViewModel
import com.example.characters.presentation.character_list.components.NoInternet
import com.example.characters.presentation.favorites.FavoritesScreen
import com.example.characters.presentation.ui.MealTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: AnimeListViewModel = hiltViewModel()
                    val hasNetwork by viewModel.hasNetwork
                    val isRetrying by viewModel.isRetrying

                    if (!hasNetwork) {
                        NoInternet(isRetrying) {
                            viewModel.retry()
                        }
                    } else {
                        val items = listOf(
                            Screen.AnimeListScreen,
                            Screen.FavoritesScreen
                        )

                        Scaffold(
                            bottomBar = {
                                BottomNavigationBar(
                                    items = items,
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        ) { paddingValues ->
                            NavHost(
                                navController = navController,
                                startDestination = Screen.AnimeListScreen.route,
                                modifier = Modifier.padding(paddingValues)
                            ) {
                                composable(route = Screen.AnimeListScreen.route) {
                                    AnimeListScreen(applicationContext, navController)
                                }

                                composable(route = Screen.FavoritesScreen.route) {
                                    FavoritesScreen(navController)
                                }

                                composable(
                                    route = Screen.AnimeDetailScreen.route,
                                    arguments = listOf(
                                        androidx.navigation.navArgument("animeId") {
                                            type = NavType.IntType
                                        }
                                    )
                                ) {
                                    AnimeDetailScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
