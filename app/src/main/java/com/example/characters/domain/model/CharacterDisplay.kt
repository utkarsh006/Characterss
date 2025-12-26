package com.example.characters.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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