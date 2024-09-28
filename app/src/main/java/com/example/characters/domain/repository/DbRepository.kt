package com.example.characters.domain.repository

import com.example.characters.domain.model.CharacterDisplay
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    fun getItems() : Flow<List<CharacterDisplay>>

    suspend fun getItemById(id:Int) : CharacterDisplay?

    suspend fun insertFavItem(item: CharacterDisplay)

    suspend fun deleteItem(item: CharacterDisplay)
}