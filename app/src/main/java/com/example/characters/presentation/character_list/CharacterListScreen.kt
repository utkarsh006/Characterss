package com.example.characters.presentation.character_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.characters.presentation.Screen
import com.example.characters.presentation.character_list.components.CharacterListItem
import com.example.characters.presentation.character_list.components.NoInternet
import com.example.characters.presentation.character_list.components.SearchComponent
import com.google.gson.Gson

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val hasNetwork = viewModel.hasNetwork.value
    val isRetrying = viewModel.isRetrying.value
    val searchText by viewModel.searchText.collectAsState()

    val displayedCharacters = remember(state.characters, searchText) {
        if (searchText.isBlank()) {
            state.characters
        } else {
            viewModel.searchCharacter(state.characters)
        }
    }

    if (hasNetwork) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchComponent(
                searchText = searchText,
                isSearching = viewModel.isSearching.collectAsState().value,
                onSearchTextChange = { viewModel.onSearchTextChange(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LazyColumn for character list
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(displayedCharacters) { particularCharacter ->
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

                // Displaying error message
                if (state.error.isNotBlank()) {
                    item {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                    }
                }

                // Loading indicator
                if (state.isLoading) {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    } else {
        NoInternet(
            isRetrying = isRetrying,
            onRetry = { viewModel.retry() }
        )
    }
}