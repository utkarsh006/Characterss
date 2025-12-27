package com.example.characters.data.repository

import com.example.characters.data.local.CharacterDao
import com.example.characters.data.remote.AnimeApi
import com.example.characters.data.remote.dto.AnimeDTO
import com.example.characters.data.remote.dto.AnimeDetailDTO
import com.example.characters.domain.model.AnimeDisplay
import com.example.characters.domain.model.CachedAnimeList
import com.example.characters.domain.repository.AnimeRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AnimeRepoImpl @Inject constructor(
    private val api: AnimeApi,
    private val dao: CharacterDao
) : AnimeRepository {

    private val gson = Gson()
    private val CACHE_VALIDITY_DURATION = 24 * 60 * 60 * 1000L // 24 hours in milliseconds

    override suspend fun getTopAnime(): AnimeDTO {
        return api.getTopAnime()
    }

    override suspend fun getAnimeDetails(animeId: Int): AnimeDetailDTO {
        return api.getAnimeDetails(animeId)
    }

    override suspend fun getCachedAnimeList(): List<AnimeDisplay>? {
        val cachedData = dao.getCachedAnimeList()
        return cachedData?.let {
            try {
                val type = object : TypeToken<List<AnimeDisplay>>() {}.type
                val animeList = gson.fromJson(it.animeListJson, type) as? List<AnimeDisplay>
                if (animeList != null && animeList.isNotEmpty()) {
                    animeList
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun saveAnimeListToCache(animeList: List<AnimeDisplay>) {
        val jsonString = gson.toJson(animeList)
        val cachedAnimeList = CachedAnimeList(
            id = 1,
            animeListJson = jsonString,
            lastUpdated = System.currentTimeMillis()
        )
        dao.insertCachedAnimeList(cachedAnimeList)
    }

    override suspend fun isCacheValid(): Boolean {
        val cachedData = dao.getCachedAnimeList()
        return cachedData?.let {
            val currentTime = System.currentTimeMillis()
            (currentTime - it.lastUpdated) < CACHE_VALIDITY_DURATION
        } ?: false
    }
}
