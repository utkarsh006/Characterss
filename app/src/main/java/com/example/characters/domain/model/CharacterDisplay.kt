package com.example.characters.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.characters.data.remote.dto.Location

@Entity
data class CharacterDisplay(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val location: Location,
    val status: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            "${name.first()}",
            "${name.last()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}