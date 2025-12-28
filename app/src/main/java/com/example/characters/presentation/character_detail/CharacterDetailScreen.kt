package com.example.characters.presentation.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.characters.common.Constants.SECTION_SPACING
import com.example.characters.common.Constants.TEXT_SPACING
import com.example.characters.domain.model.AnimeDetail
import com.example.characters.presentation.character_detail.components.AppText
import com.example.characters.presentation.character_detail.components.MediaSection
import com.example.characters.presentation.character_detail.components.TextType

@Composable
fun AnimeDetailScreen(
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            state.error.isNotBlank() -> ErrorMessage(state.error)
            state.animeDetail != null -> CharacterDetailContent(state.animeDetail)
        }
    }
}

@Composable
private fun ErrorMessage(error: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        AppText(
            text = error,
            type = TextType.ERROR,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun CharacterDetailContent(animeDetail: AnimeDetail) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(SECTION_SPACING.dp),
        verticalArrangement = Arrangement.spacedBy(SECTION_SPACING.dp)
    ) {
        item { MediaSection(animeDetail) }
        item { TitleSection(animeDetail.title) }
        item { SynopsisSection(animeDetail.synopsis) }
        item { GenresSection(animeDetail.genres) }
        item { CastSection(animeDetail.mainCast) }
        item { EpisodesAndRatingSection(animeDetail.episodes, animeDetail.rating) }
    }
}

@Composable
private fun TitleSection(title: String) {
    AppText(
        text = title,
        type = TextType.TITLE,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun SynopsisSection(synopsis: String) {
    Section(title = "Synopsis") {
        AppText(
            text = synopsis,
            type = TextType.BODY,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun GenresSection(genres: List<String>) {
    Section(title = "Genres") {
        AppText(
            text = genres.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "No genres available.",
            type = TextType.BODY
        )
    }
}

@Composable
private fun CastSection(cast: List<String>) {
    Section(title = "Main Cast") {
        if (cast.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                cast.forEach { castMember ->
                    AppText("• $castMember", type = TextType.BODY)
                }
            }
        } else {
            AppText("No cast information available.", type = TextType.BODY)
        }
    }
}

@Composable
private fun EpisodesAndRatingSection(episodes: Int, rating: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoColumn("Episodes", if (episodes > 0) episodes.toString() else "Ongoing/Unknown")
        InfoColumn("Rating", rating, Alignment.End)
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    AppText(title, type = TextType.SECTION_HEADER)
    Spacer(modifier = Modifier.height(TEXT_SPACING.dp))
    content()
}

@Composable
private fun InfoColumn(
    title: String,
    value: String,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(horizontalAlignment = alignment) {
        AppText(title, type = TextType.BODY_BOLD)
        AppText(value, type = TextType.BODY)
    }
}