package com.example.characters.domain.usecases

import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoveFavorites @Inject constructor(private val repository: DbRepository) {

    suspend operator fun invoke(character: CharacterDisplay) {
        repository.deleteItem(character)
    }

    fun fetch(): Flow<List<CharacterDisplay>> {
        return repository.getItems()
    }
}
