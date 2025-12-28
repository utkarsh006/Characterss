package com.example.characters.navigation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.characters.presentation.ui.CharacterTheme

@Composable
fun AppRoot(context: Context) {
    CharacterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
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
                AppNavHost(
                    navController = navController,
                    context = context,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
