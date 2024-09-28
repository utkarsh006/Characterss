package com.example.characters.domain.model

data class CharacterDisplay(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            "${name.first()}",
            "${name.last()}",
        )
        return matchingCombinations.any() {
            it.contains(query, ignoreCase = true)
        }
    }
}