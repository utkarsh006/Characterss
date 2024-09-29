package com.example.characters.presentation.favorites

import com.example.characters.domain.model.CharacterDisplay

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<CharacterDisplay> = emptyList(),
    val error: String = "",
)