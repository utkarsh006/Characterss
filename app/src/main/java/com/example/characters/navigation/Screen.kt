package com.example.characters.navigation

sealed class Screen(val route: String) {
    object CharacterListScreen : Screen("Home")
    object CharacterDetailScreen : Screen("Details")
    object FavoritesScreen: Screen("Favorites")
}
