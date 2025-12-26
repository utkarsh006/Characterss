package com.example.characters.presentation.favorites

import com.example.characters.domain.model.AnimeDisplay

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<AnimeDisplay> = emptyList(),
    val error: String = "",
)