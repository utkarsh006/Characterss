package com.example.characters.domain.usecases

import com.example.characters.common.Resource
import com.example.characters.data.remote.dto.toCharacter
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    private var characterName: String ?= null

    fun setCityName(_characterName: String){
        this.characterName = _characterName
    }

    operator fun invoke(): Flow<Resource<List<CharacterDisplay>>> = flow {
        try {
            emit(Resource.Loading())
            val meals = repository.getCharacters().map { it.toCharacter() }
            emit(Resource.Success(meals))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach Server"))
        }
    }
}