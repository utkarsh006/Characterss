package com.example.characters.domain.usecases

data class AllUseCases(
    val fetchCharacters: FetchCharacters,
    val getAnimeUseCase: GetAnimeUseCase,
    val removeFavorites: RemoveFavorites,
    val saveCharacter: SaveCharacter
)