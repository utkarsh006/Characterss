package com.example.characters.data.remote

import com.example.characters.common.Constants
import com.example.characters.data.remote.dto.Character
import retrofit2.http.GET

interface CharacterApi {
    @GET(Constants.CHARACTERS_API_ENDPOINT)
    suspend fun getCharacters(): Character
}

/* This is a Retrofit Api interface, we basically defines different functions and routes we want to
access from the api */
