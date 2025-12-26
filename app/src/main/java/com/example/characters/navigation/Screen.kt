package com.example.characters.navigation

sealed class Screen(val route: String) {
    object AnimeListScreen : Screen("Home")
    object FavoritesScreen: Screen("Favorites")
}
