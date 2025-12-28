package com.example.characters.presentation.character_detail.components

import androidx.compose.runtime.Composable
import com.example.characters.domain.model.AnimeDetail

@Composable
fun MediaSection(animeDetail: AnimeDetail) {
    when {
        !animeDetail.trailerUrl.isNullOrBlank() -> TrailerView(animeDetail.trailerUrl)
        animeDetail.posterImageUrl.isNotBlank() -> PosterImage(animeDetail.posterImageUrl)
        else -> NoMediaPlaceholder()
    }
}