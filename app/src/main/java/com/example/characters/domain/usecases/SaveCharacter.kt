package com.example.characters.domain.usecases

import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveCharacter @Inject constructor(private val repository: DbRepository) {

    suspend operator fun invoke(character: CharacterDisplay) {
        require(character.name.isNotBlank()) { "Character name cannot be blank" }
        require(character.species.isNotBlank()) { "Character species cannot be blank" }
        require(character.gender.isNotBlank()) { "Character gender cannot be blank" }
        repository.insertFavItem(character)
    }

    fun fetch(): Flow<List<CharacterDisplay>> {
        return repository.getItems()
    }
}
