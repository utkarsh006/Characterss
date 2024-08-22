package com.example.characters.presentation.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.characters.presentation.Screen
import com.example.characters.presentation.character_list.components.CharacterListItem
import com.example.characters.presentation.character_list.components.NoInternet
import com.google.gson.Gson

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val hasNetwork = viewModel.hasNetwork.value
    val isRetrying = viewModel.isRetrying.value

    if (hasNetwork) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.meals) { particularCharacter ->
                    CharacterListItem(
                        character = particularCharacter,
                        onItemClicked = {
                            val navMapper = Gson().toJson(particularCharacter)
                            navController.navigate(
                                Screen.CharacterDetailScreen.route + "?id=$navMapper"
                            )
                        }
                    )
                }
            }

            if (state.error.isNotBlank()) {
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

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    } else {
        NoInternet(
            isRetrying = isRetrying,
            onRetry = { viewModel.retry() }
        )
    }
}