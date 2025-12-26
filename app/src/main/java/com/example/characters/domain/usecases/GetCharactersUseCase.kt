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
            val animeList = repository.getTopAnime().data.map { it.toAnimeDisplay() }
            emit(Resource.Success(animeList))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach Server"))
        }
    }
}