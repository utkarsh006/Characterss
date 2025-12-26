package com.example.characters.domain.repository

import com.example.characters.data.remote.dto.AnimeDTO

interface AnimeRepository {
    suspend fun getTopAnime(): AnimeDTO
}