package com.example.characters.data.repository

import com.example.characters.data.local.CharacterDao
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow

class DbRepoImpl(
    private val dao: CharacterDao
) : DbRepository {
    override fun getItems(): Flow<List<CharacterDisplay>> {
        return dao.getItems()
    }

    override suspend fun getItemById(id: Int): CharacterDisplay? {
        return dao.getItemById(id)
    }

    override suspend fun insertFavItem(item: CharacterDisplay) {
        dao.insertFavItem(item)
    }

    override suspend fun deleteItem(item: CharacterDisplay) {
        dao.deleteItem(item)
    }
}