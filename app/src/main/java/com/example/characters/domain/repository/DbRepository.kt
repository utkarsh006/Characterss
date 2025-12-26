package com.example.characters.domain.repository

import com.example.characters.domain.model.AnimeDisplay
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    fun getItems() : Flow<List<AnimeDisplay>>

    suspend fun getItemById(id:Int) : AnimeDisplay?

    suspend fun insertFavItem(item: AnimeDisplay)

    suspend fun deleteItem(item: AnimeDisplay)
}