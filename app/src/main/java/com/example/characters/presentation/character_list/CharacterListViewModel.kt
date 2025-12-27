package com.example.characters.presentation.character_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.common.Resource
import com.example.characters.domain.model.AnimeDisplay
import com.example.characters.domain.usecases.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AnimeListState())
    val state: State<AnimeListState> get() = _state


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    init {
        loadTopAnime()
    }


    private fun loadTopAnime() {
        viewModelScope.launch {
            allUseCases.getAnimeUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = AnimeListState(anime = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value =
                            AnimeListState(error = result.message ?: "Unexpected Error")
                    }

                    is Resource.Loading -> {
                        _state.value = AnimeListState(isLoading = true)
                    }
                }
            }.launchIn(this)
        }
    }


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun searchAnime(animeList: List<AnimeDisplay>): List<AnimeDisplay> {
        return if (searchText.value.isEmpty()) {
            animeList
        } else {
            animeList.filter {
                it.doesMatchSearchQuery(searchText.value)
            }
        }
    }

}
