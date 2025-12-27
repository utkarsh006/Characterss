package com.example.characters.presentation.character_detail

import com.example.characters.domain.model.AnimeDetail

data class AnimeDetailState(
    val isLoading: Boolean = false,
    val animeDetail: AnimeDetail? = null,
    val error: String = ""
)