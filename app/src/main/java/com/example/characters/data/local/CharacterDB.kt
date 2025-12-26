package com.example.characters.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.characters.domain.model.AnimeDisplay

@Database(
    entities = [AnimeDisplay::class],
    version = 1
)
abstract class CharacterDB : RoomDatabase() {
    abstract val characterDao: CharacterDao

    companion object {
        const val DB_NAME = "character_db"
    }
}