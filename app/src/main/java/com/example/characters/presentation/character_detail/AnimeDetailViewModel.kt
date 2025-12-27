package com.example.characters.presentation.character_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.common.Resource
import com.example.characters.domain.usecases.GetAnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AnimeDetailState())
    val state: State<AnimeDetailState> = _state

    init {
        savedStateHandle.get<Int>("animeId")?.let { animeId ->
            if (animeId > 0) {
                getAnimeDetails(animeId)
            } else {
                _state.value = AnimeDetailState(error = "Invalid anime ID")
            }
        } ?: run {
            _state.value = AnimeDetailState(error = "Anime ID not provided")
        }
    }

    private fun getAnimeDetails(animeId: Int) {
        getAnimeDetailsUseCase(animeId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AnimeDetailState(animeDetail = result.data)
                }
                is Resource.Error -> {
                    _state.value = AnimeDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}