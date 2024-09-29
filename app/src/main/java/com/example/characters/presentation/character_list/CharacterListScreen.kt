package com.example.characters.presentation.character_list

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.characters.navigation.Screen
import com.example.characters.presentation.character_list.components.CharacterListItem
import com.example.characters.presentation.character_list.components.NoInternet
import com.example.characters.presentation.character_list.components.SearchComponent
import com.example.characters.presentation.character_list.components.SearchNotFoundUi
import com.google.gson.Gson

@Composable
fun CharacterListScreen(
    context: Context,
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

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // Check if displayedCharacters is empty to show NoSearchUi
                if (displayedCharacters.isEmpty() && searchText.isNotBlank()) {
                    item {
                        SearchNotFoundUi()
                    }
                } else {
                    items(displayedCharacters) { particularCharacter ->
                        CharacterListItem(
                            context = context,
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