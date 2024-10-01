package com.example.characters.presentation.favorites

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.presentation.character_list.components.TextComponent
import com.example.characters.presentation.favorites.components.FavoriteCharacterItem
import com.example.characters.presentation.favorites.components.NoFavoritesUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    viewModel.getCharacters()
    val state = viewModel.favState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.favorites.isEmpty() -> {
                NoFavoritesUi()
            }

            else -> {
                FavoritesList(state.favorites, viewModel)
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
fun FavoritesList(favoriteCharacters: List<CharacterDisplay>, viewModel: FavoritesViewModel) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(favoriteCharacters) { character ->
            val dismissState = rememberSwipeToDismissBoxState(
                initialValue = SwipeToDismissBoxValue.Settled,
                confirmValueChange = { dismissValue ->
                    if (dismissValue == SwipeToDismissBoxValue.StartToEnd) {
                        handleCharacterRemoval(character, viewModel, scope, context)
                        true // Allow dismiss
                    } else {
                        false // Do not allow other dismiss actions
                    }
                }
            )

            SwipeToDismissBox(
                state = dismissState,
                modifier = Modifier.fillMaxWidth(),
                backgroundContent = { },
                content = { FavoriteCharacterItem(character) }
            )
        }
    }
}

private fun handleCharacterRemoval(
    character: CharacterDisplay,
    viewModel: FavoritesViewModel,
    scope: CoroutineScope,
    context: Context
) {
    scope.launch {
        try {
            viewModel.removeCharacter(character)
            Toast.makeText(context, "${character.name} removed from favorites", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to remove ${character.name} from favorites", Toast.LENGTH_SHORT).show()
        }
    }
}