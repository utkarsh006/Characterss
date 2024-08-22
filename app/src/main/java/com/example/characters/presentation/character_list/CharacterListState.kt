package com.example.characters.presentation.character_list

import com.example.characters.domain.model.CharacterDisplay

data class CharacterListState(
    val isLoading: Boolean = false,
    val meals: List<CharacterDisplay> = emptyList(),
    val error: String = ""
)
