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
import androidx.navigation.navArgument
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.navigation.BottomNavigationBar
import com.example.characters.navigation.Screen
import com.example.characters.presentation.character_detail.CharacterDetailScreen
import com.example.characters.presentation.character_list.CharacterListScreen
import com.example.characters.presentation.character_list.CharacterListViewModel
import com.example.characters.presentation.character_list.components.NoInternet
import com.example.characters.presentation.ui.MealTheme
import com.google.gson.Gson
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
                    val viewModel: CharacterListViewModel = hiltViewModel()
                    val hasNetwork by viewModel.hasNetwork
                    val isRetrying by viewModel.isRetrying

                    if (!hasNetwork) {
                        NoInternet(isRetrying) {
                            viewModel.retry()
                        }
                    } else {
                        val items = listOf(
                            Screen.CharacterListScreen,
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
                                startDestination = Screen.CharacterListScreen.route,
                                modifier = Modifier.padding(paddingValues)
                            ) {
                                composable(route = Screen.CharacterListScreen.route) {
                                    CharacterListScreen(navController)
                                }

                                composable(route = Screen.FavoritesScreen.route) {
                                    FavoritesScreen(navController)
                                }

                                composable(
                                    route = Screen.CharacterDetailScreen.route + "?id={id}",
                                    arguments = listOf(navArgument("id") {
                                        type = NavType.StringType
                                    })
                                ) { backStackEntry ->
                                    val meal = Gson().fromJson(
                                        backStackEntry.arguments?.getString("id"),
                                        CharacterDisplay::class.java
                                    )
                                    CharacterDetailScreen(meal)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
