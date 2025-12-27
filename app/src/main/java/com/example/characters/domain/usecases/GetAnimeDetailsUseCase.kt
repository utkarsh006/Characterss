package com.example.characters.domain.usecases

import com.example.characters.common.Resource
import com.example.characters.data.remote.dto.toAnimeDetail
import com.example.characters.domain.model.AnimeDetail
import com.example.characters.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(animeId: Int): Flow<Resource<AnimeDetail>> = flow {
        try {
            emit(Resource.Loading())
            val animeDetail = repository.getAnimeDetails(animeId).data.toAnimeDetail()
            emit(Resource.Success(animeDetail))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach Server"))
        }
    }
}