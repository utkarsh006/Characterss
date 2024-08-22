package com.example.characters.domain.repository

import com.example.characters.data.remote.dto.ResultDTO

interface CharacterRepository {
    suspend fun getCharacters(): List<ResultDTO>
}