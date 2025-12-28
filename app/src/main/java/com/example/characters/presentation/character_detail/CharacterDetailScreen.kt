package com.example.characters.presentation.character_detail

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.characters.domain.model.AnimeDetail

@Composable
fun AnimeDetailScreen(
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            state.animeDetail != null -> {
                AnimeDetailContent(state.animeDetail)
            }
        }
    }
}

@Composable
private fun AnimeDetailContent(animeDetail: AnimeDetail) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Video Player or Poster Image
        item {
            Log.d("AnimeDetail", "Trailer URL: ${animeDetail.trailerUrl}")
            Log.d("AnimeDetail", "Poster URL: ${animeDetail.posterImageUrl}")

            when {
                !animeDetail.trailerUrl.isNullOrBlank() -> {
                    Log.d("AnimeDetail", "Loading WebView with URL: ${animeDetail.trailerUrl}")

                    // Use Box with fixed height to force WebView to render
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        AndroidView(
                            factory = { context ->
                                WebView(context).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    settings.apply {
                                        javaScriptEnabled = true
                                        domStorageEnabled = true
                                        databaseEnabled = true
                                        mediaPlaybackRequiresUserGesture = false
                                        loadWithOverviewMode = true
                                        useWideViewPort = true
                                        setSupportZoom(false)
                                        allowFileAccess = true
                                        allowContentAccess = true
                                        mixedContentMode =
                                            android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                                    }
                                    webViewClient = object : WebViewClient() {
                                        override fun onPageFinished(view: WebView?, url: String?) {
                                            super.onPageFinished(view, url)
                                            Log.d("AnimeDetail", "WebView page loaded: $url")
                                        }

                                        override fun onReceivedError(
                                            view: WebView?,
                                            errorCode: Int,
                                            description: String?,
                                            failingUrl: String?
                                        ) {
                                            super.onReceivedError(
                                                view,
                                                errorCode,
                                                description,
                                                failingUrl
                                            )
                                            Log.e("AnimeDetail", "WebView error: $description")
                                        }
                                    }
                                    webChromeClient = WebChromeClient()
                                }
                            },
                            update = { webView ->
                                webView.loadUrl(animeDetail.trailerUrl)
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                animeDetail.posterImageUrl.isNotBlank() -> {
                    Log.d("AnimeDetail", "Loading Image with URL: ${animeDetail.posterImageUrl}")

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(animeDetail.posterImageUrl)
                            .crossfade(true)
                            .listener(
                                onSuccess = { _, _ ->
                                    Log.d("AnimeDetail", "Image loaded successfully")
                                },
                                onError = { _, result ->
                                    Log.e(
                                        "AnimeDetail",
                                        "Image load error: ${result.throwable.message}"
                                    )
                                }
                            )
                            .build(),
                        contentDescription = "Anime Poster",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No media available",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Title
        item {
            Text(
                text = animeDetail.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Synopsis
        item {
            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = animeDetail.synopsis,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }

        // Genres
        item {
            Text(
                text = "Genres",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (animeDetail.genres.isNotEmpty()) {
                    animeDetail.genres.joinToString(", ")
                } else {
                    "No genres available."
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Main Cast
        item {
            Text(
                text = "Main Cast",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (animeDetail.mainCast.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    animeDetail.mainCast.forEach { castMember ->
                        Text(
                            text = "• $castMember",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                Text(
                    text = "No cast information available.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Episodes and Rating
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Episodes",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (animeDetail.episodes > 0) {
                            animeDetail.episodes.toString()
                        } else {
                            "Ongoing/Unknown"
                        },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Rating",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = animeDetail.rating,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}