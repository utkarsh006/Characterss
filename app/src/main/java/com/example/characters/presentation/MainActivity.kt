package com.example.characters.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
                    val items = listOf(
                        Screen.CharacterListScreen,
                        Screen.FavoritesScreen // Add Favorites Screen
                    )

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = items,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route) {
                                        // Ensures the user doesn't add duplicate destinations to the stack
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CharacterListScreen.route,
                            modifier = Modifier.padding(it).fillMaxWidth()
                        ) {
                            composable(
                                route = Screen.CharacterListScreen.route
                            ) {
                                CharacterListScreen(navController)
                            }

                            composable(
                                route = Screen.FavoritesScreen.route
                            ) {
                                FavoritesScreen(navController)
                            }

                            composable(
                                route = Screen.CharacterDetailScreen.route + "?id={id}",
                                arguments = listOf(
                                    navArgument("id") {
                                        type = NavType.StringType
                                    }
                                )
                            ) {
                                val meal = Gson().fromJson(it.arguments?.getString("id"), CharacterDisplay::class.java)
                                CharacterDetailScreen(meal)
                            }
                        }
                    }
                }
            }
        }
    }
}