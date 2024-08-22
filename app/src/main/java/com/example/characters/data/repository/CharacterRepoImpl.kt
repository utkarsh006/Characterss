package com.example.characters.data.repository

import com.example.characters.data.remote.CharacterApi
import com.example.characters.data.remote.dto.ResultDTO
import com.example.characters.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(
    private val api: CharacterApi
) : CharacterRepository {

    override suspend fun getCharacters(): List<ResultDTO> {
        val response = api.getCharacters()
        // Log.d("MyTag", response.toString())
        return response.results
    }
}
