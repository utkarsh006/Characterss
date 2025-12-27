package com.example.characters.navigation

sealed class Screen(val route: String) {
    object AnimeListScreen : Screen("Home")
    object FavoritesScreen: Screen("Favorites")
    object AnimeDetailScreen : Screen("anime_detail/{animeId}") {
        fun createRoute(animeId: Int) = "anime_detail/$animeId"
    }
}
