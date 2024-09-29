package com.example.characters.presentation.favorites

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.presentation.character_list.components.TextComponent
import com.example.crypto.R

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val state = viewModel.favState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("#HOLA", state.toString())
        Log.d("#DEBUG", state.favorites.toString())
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.favorites.isEmpty() -> {
                NoFavoritesUi()
            }

            else -> {
                FavoritesList(state.favorites)
            }
        }

        if (state.error.isNotEmpty()) {
            TextComponent(
                text = state.error,
                fontWeight = FontWeight.Normal,
                fontSize = 18f,
                color = Color.Black,
                padding = 8.dp
            )
        }
    }
}

@Composable
fun FavoritesList(favoriteCharacters: List<CharacterDisplay>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(favoriteCharacters) { character ->
            FavoriteCharacterItem(character)
        }
    }
}

@Composable
fun FavoriteCharacterItem(character: CharacterDisplay) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .clickable { /* Handle click event */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        TextComponent(
            text = "No Favorites Yet !!",
            fontWeight = FontWeight.Normal,
            fontSize = 12f,
            color = Color.Black,
            padding = 8.dp
        )
    }
}

@Composable
fun NoFavoritesUi() {

    Image(
        painter = painterResource(id = R.drawable.ic_no_favs),
        contentDescription = "No Favorites Saved",
        modifier = Modifier.size(200.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = "No Favorites Yet !!",
        fontWeight = FontWeight.SemiBold,
        fontSize = 18f,
        color = Color.Black,
        padding = 8.dp
    )
}