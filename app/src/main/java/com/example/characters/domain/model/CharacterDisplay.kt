package com.example.characters.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class AnimeDisplay(
    @PrimaryKey
    val id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val imageUrl: String,
    val synopsis: String?,
    val year: Int?,
    val status: String?
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            title,
            "${title.first()}",
            "${title.last()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

@Entity(tableName = "cached_anime_list")
data class CachedAnimeList(
    @PrimaryKey
    val id: Int = 1, // Single row for the entire list
    val animeListJson: String, // JSON string of the anime list
    val lastUpdated: Long = System.currentTimeMillis() // Timestamp of last update
)

data class AnimeDetail(
    val id: Int,
    val title: String,
    val synopsis: String,
    val genres: List<String>,
    val episodes: Int,
    val rating: String,
    val trailerUrl: String?,
    val posterImageUrl: String,
    val mainCast: List<String>
)