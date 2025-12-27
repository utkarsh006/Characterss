package com.example.characters.domain.repository

import com.example.characters.data.remote.dto.AnimeDTO
import com.example.characters.data.remote.dto.AnimeDetailDTO
import com.example.characters.domain.model.AnimeDisplay

interface AnimeRepository {
    suspend fun getTopAnime(): AnimeDTO
    suspend fun getAnimeDetails(animeId: Int): AnimeDetailDTO

    // Cache operations
    suspend fun getCachedAnimeList(): List<AnimeDisplay>?
    suspend fun saveAnimeListToCache(animeList: List<AnimeDisplay>)
    suspend fun isCacheValid(): Boolean
}