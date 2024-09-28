package com.example.characters.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.characters.domain.model.CharacterDisplay
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characterDisplay")
    fun getItems(): Flow<List<CharacterDisplay>>

    @Query("SELECT * FROM characterDisplay WHERE id = :id")
    suspend fun getItemById(id: Int): CharacterDisplay?

    //if we call INSERT function with an existing id, it will update the existing entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavItem(character: CharacterDisplay)

    @Delete
    suspend fun deleteItem(character: CharacterDisplay)

}