package com.example.characters.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.characters.domain.model.AnimeDisplay
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

}