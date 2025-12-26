package com.example.characters.data.repository

import com.example.characters.data.remote.AnimeApi
import com.example.characters.data.remote.dto.AnimeDTO
import com.example.characters.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepoImpl @Inject constructor(
    private val api: AnimeApi
) : AnimeRepository {

    override suspend fun getTopAnime(): AnimeDTO {
        return api.getTopAnime()
    }
}
