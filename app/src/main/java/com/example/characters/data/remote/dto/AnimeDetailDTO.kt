package com.example.characters.data.remote.dto

import com.example.characters.domain.model.AnimeDetail

data class AnimeDetailDTO(
    val `data`: Data
) {
    data class Data(
        val aired: Aired,
        val airing: Boolean,
        val approved: Boolean,
        val background: String?,
        val broadcast: Broadcast,
        val demographics: List<Demographic>,
        val duration: String,
        val episodes: Int,
        val explicit_genres: List<Any>,
        val favorites: Int,
        val genres: List<Genre>,
        val images: Images,
        val licensors: List<Licensor>,
        val mal_id: Int,
        val members: Int,
        val popularity: Int,
        val producers: List<Producer>,
        val rank: Int,
        val rating: String,
        val score: Double,
        val scored_by: Int,
        val season: String,
        val source: String,
        val status: String,
        val studios: List<Studio>,
        val synopsis: String?,
        val themes: List<Any>,
        val title: String,
        val title_english: String?,
        val title_japanese: String?,
        val title_synonyms: List<String>,
        val titles: List<Title>,
        val trailer: Trailer,
        val type: String,
        val url: String,
        val year: Int
    ) {
        data class Aired(
            val from: String,
            val prop: Prop,
            val string: String,
            val to: String
        ) {
            data class Prop(
                val from: From,
                val to: To
            ) {
                data class From(
                    val day: Int,
                    val month: Int,
                    val year: Int
                )

                data class To(
                    val day: Int,
                    val month: Int,
                    val year: Int
                )
            }
        }

        data class Broadcast(
            val day: String,
            val string: String,
            val time: String,
            val timezone: String
        )

        data class Demographic(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )

        data class Genre(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )

        data class Images(
            val jpg: Jpg,
            val webp: Webp
        ) {
            data class Jpg(
                val image_url: String,
                val large_image_url: String,
                val small_image_url: String
            )

            data class Webp(
                val image_url: String,
                val large_image_url: String,
                val small_image_url: String
            )
        }

        data class Licensor(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )

        data class Producer(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )

        data class Studio(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )

        data class Title(
            val title: String,
            val type: String
        )

        data class Trailer(
            val embed_url: String,
            val images: Images,
            val url: Any,
            val youtube_id: Any
        ) {
            data class Images(
                val image_url: Any,
                val large_image_url: Any,
                val maximum_image_url: Any,
                val medium_image_url: Any,
                val small_image_url: Any
            )
        }
    }
}

fun AnimeDetailDTO.Data.toAnimeDetail(): AnimeDetail {
    return AnimeDetail(
        id = mal_id,
        title = title.takeIf { !it.isNullOrBlank() } ?: "Unknown Title",
        synopsis = synopsis.takeIf { !it.isNullOrBlank() } ?: "No synopsis available.",
        genres = genres.mapNotNull { it.name.takeIf { name -> !name.isNullOrBlank() } },
        episodes = episodes,
        rating = String.format("%.1f", score),
        trailerUrl = trailer.embed_url.takeIf { !it.isNullOrBlank() },
        posterImageUrl = images.jpg.large_image_url.takeIf { !it.isNullOrBlank() }
            ?: images.jpg.image_url.takeIf { !it.isNullOrBlank() }
            ?: images.webp.large_image_url.takeIf { !it.isNullOrBlank() }
            ?: images.webp.image_url.takeIf { !it.isNullOrBlank() }
            ?: "",
        mainCast = emptyList() // Characters data not available in current DTO structure
    )
}