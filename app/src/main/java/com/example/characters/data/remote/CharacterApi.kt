package com.example.characters.data.remote

import com.example.characters.common.Constants
import com.example.characters.data.remote.dto.AnimeDTO
import com.example.characters.data.remote.dto.AnimeDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {
    @GET(Constants.ANIME_API_ENDPOINT)
    suspend fun getTopAnime(): AnimeDTO

    @GET(Constants.ANIME_DETAILS_API_ENDPOINT)
    suspend fun getAnimeDetails(
        @Path("id") animeId: Int
    ): AnimeDetailDTO
}
