package com.example.characters.data.repository

import com.example.characters.data.local.CharacterDao
import com.example.characters.domain.model.AnimeDisplay
import com.example.characters.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow

class DbRepoImpl(
    private val dao: CharacterDao
) : DbRepository {
    override fun getItems(): Flow<List<AnimeDisplay>> {
        return dao.getItems()
    }

    override suspend fun getItemById(id: Int): AnimeDisplay? {
        return dao.getItemById(id)
    }

    override suspend fun insertFavItem(item: AnimeDisplay) {
        dao.insertFavItem(item)
    }

    override suspend fun deleteItem(item: AnimeDisplay) {
        dao.deleteItem(item)
    }
}