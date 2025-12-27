package com.example.characters.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.characters.domain.model.AnimeDisplay
import com.example.characters.domain.model.CachedAnimeList

@Database(
    entities = [AnimeDisplay::class, CachedAnimeList::class],
    version = 2
)
abstract class CharacterDB : RoomDatabase() {
    abstract val characterDao: CharacterDao

    companion object {
        const val DB_NAME = "character_db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new cached_anime_list table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `cached_anime_list` (
                        `id` INTEGER NOT NULL,
                        `animeListJson` TEXT NOT NULL,
                        `lastUpdated` INTEGER NOT NULL,
                        PRIMARY KEY(`id`)
                    )
                """)
            }
        }
    }
}