package com.example.characters.presentation.character_list.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.presentation.character_list.CharacterListViewModel
import com.example.characters.presentation.favorites.FavoritesViewModel

@Composable
fun CharacterListItem(
    context: Context,
    character: CharacterDisplay,
    onItemClicked: (CharacterDisplay) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val isFavorite = remember { mutableStateOf(false) }

    // Observe favorites to check if the character is already saved
    LaunchedEffect(key1 = favoritesViewModel.favState.value.favorites) {
        isFavorite.value = favoritesViewModel.favState.value.favorites.any { it.id == character.id }
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onItemClicked(character) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // First Column: Image
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Second Column: Text and Icon
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(CenterVertically)
                    )

                    IconButton(onClick = {
                        if (isFavorite.value) {
                            favoritesViewModel.removeCharacter(character)
                            Toast.makeText(context, "Removed from Favorites", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            favoritesViewModel.saveCharacter(character)
                            Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
                        }
                        isFavorite.value = !isFavorite.value
                    }) {
                        Icon(
                            imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite.value) "Remove" else "Save"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${character.gender} ● ${character.species} ● ${character.location.name}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}