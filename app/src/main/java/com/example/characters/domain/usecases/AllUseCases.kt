package com.example.characters.domain.usecases

data class AllUseCases(
    val fetchCharacters: FetchCharacters,
    val getCharactersUseCase: GetCharactersUseCase,
    val removeFavorites: RemoveFavorites,
    val saveCharacter: SaveCharacter
)