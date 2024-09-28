package com.example.characters.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.characters.domain.model.CharacterDisplay

@Database(
    entities = [CharacterDisplay::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CharacterDB : RoomDatabase() {
    abstract val characterDao: CharacterDao

    companion object {
        const val DB_NAME = "character_db"
    }
}