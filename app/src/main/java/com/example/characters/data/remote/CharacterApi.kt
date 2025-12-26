package com.example.characters.data.remote

import com.example.characters.common.Constants
import com.example.characters.data.remote.dto.AnimeDTO
import retrofit2.http.GET

interface AnimeApi {
    @GET(Constants.ANIME_API_ENDPOINT)
    suspend fun getTopAnime(): AnimeDTO
}
