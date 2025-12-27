package com.example.characters.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.characters.domain.model.AnimeDisplay
import com.example.characters.domain.model.CachedAnimeList
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM animedisplay")
    fun getItems(): Flow<List<AnimeDisplay>>

    @Query("SELECT * FROM animedisplay WHERE id = :id")
    suspend fun getItemById(id: Int): AnimeDisplay?

    //if we call INSERT function with an existing id, it will update the existing entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavItem(character: AnimeDisplay)

    @Delete
    suspend fun deleteItem(character: AnimeDisplay)

    // Cache methods for anime list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedAnimeList(cachedAnimeList: CachedAnimeList)

    @Query("SELECT * FROM cached_anime_list WHERE id = 1")
    suspend fun getCachedAnimeList(): CachedAnimeList?

    @Query("DELETE FROM cached_anime_list WHERE id = 1")
    suspend fun clearCachedAnimeList()

}