package com.example.characters.domain.usecases

import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharacters @Inject constructor(private val repository: DbRepository) {

    fun fetch(): Flow<List<CharacterDisplay>> {
        return repository.getItems()
    }
}