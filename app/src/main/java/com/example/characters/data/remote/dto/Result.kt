package com.example.characters.data.remote.dto

import com.example.characters.domain.model.CharacterDisplay

data class ResultDTO(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun ResultDTO.toCharacter(): CharacterDisplay {
    return CharacterDisplay(
        created = created,
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        location = location,
        status = status,
    )
}