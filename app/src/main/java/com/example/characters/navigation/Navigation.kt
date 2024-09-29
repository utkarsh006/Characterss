package com.example.characters.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    items: List<Screen>,
    navController: NavController,
    onItemClick: (Screen) -> Unit
) {
    BottomAppBar {
        // Get the current route from the back stack entry
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {}
                    ) {
                        // Display specific icon for the Discover screen, others use the provided icon
                        if (screen == Screen.CharacterListScreen) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Discover Icon"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Screen Icon"
                            )
                        }
                    }
                },
                label = { Text(screen.route) },
                selected = currentRoute == screen.route,  // Check if the current route matches the screen route
                onClick = { onItemClick(screen) },
                alwaysShowLabel = true
            )
        }
    }
}
