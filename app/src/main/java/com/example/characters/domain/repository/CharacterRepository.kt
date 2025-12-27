package com.example.characters.domain.repository

import com.example.characters.data.remote.dto.AnimeDTO
import com.example.characters.data.remote.dto.AnimeDetailDTO

interface AnimeRepository {
    suspend fun getTopAnime(): AnimeDTO
    suspend fun getAnimeDetails(animeId: Int): AnimeDetailDTO
}