package com.example.characters.domain.usecases

import com.example.characters.common.Resource
import com.example.characters.data.remote.dto.toAnimeDisplay
import com.example.characters.domain.model.AnimeDisplay
import com.example.characters.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<Resource<List<AnimeDisplay>>> = flow {
        try {
            emit(Resource.Loading())

            // Always try to load from cache first
            val cachedAnimeList = repository.getCachedAnimeList()

            // If we have cached data, emit it immediately
            if (cachedAnimeList != null && cachedAnimeList.isNotEmpty()) {
                emit(Resource.Success(cachedAnimeList))

                // Now trying to refresh from network in background (only if cache is stale)
                val isCacheValid = repository.isCacheValid()
                if (!isCacheValid) {
                    try {
                        // Attempt to fetch fresh data and update cache
                        val freshAnimeList =
                            repository.getTopAnime().data.map { it.toAnimeDisplay() }
                        repository.saveAnimeListToCache(freshAnimeList)
                        // Emit updated data
                        emit(Resource.Success(freshAnimeList))
                    } catch (networkException: Exception) {
                        // Network failed, but we already showed cached data, so just continue
                        // Don't emit any error - user already sees content
                    }
                }
            } else {
                // No cached data available, try to fetch from network
                try {
                    val animeList = repository.getTopAnime().data.map { it.toAnimeDisplay() }
                    repository.saveAnimeListToCache(animeList)
                    emit(Resource.Success(animeList))
                } catch (networkException: Exception) {
                    // No cached data and network failed
                    emit(Resource.Error("No internet connection and no cached data available. Please connect to the internet to load anime data."))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach Server"))
        }
    }
}