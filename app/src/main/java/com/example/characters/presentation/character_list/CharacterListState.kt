package com.example.characters.presentation.character_list

import com.example.characters.domain.model.AnimeDisplay

data class AnimeListState(
    val isLoading: Boolean = false,
    val anime: List<AnimeDisplay> = emptyList(),
    val error: String = ""
)
