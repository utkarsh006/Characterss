package com.example.characters.presentation.favorites

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.common.Resource
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.usecases.SaveCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val saveCharacter: SaveCharacter
) : ViewModel() {

    private val _favState = mutableStateOf(FavoritesState())
    val favState: State<FavoritesState> get() = _favState

    fun saveCharacter(character: CharacterDisplay) {
        viewModelScope.launch {
            _favState.value = FavoritesState(isLoading = true)

            val result: Resource<Unit> = try {
                saveCharacter.invoke(character)
                Resource.Success(Unit)

            } catch (exception: Exception) {
                Resource.Error("Failed to save character: ${exception.message}")
            }

            when (result) {
                is Resource.Success -> {
                    val updatedFavorites = _favState.value.favorites.toMutableList().apply {
                        add(character)
                    }

                    _favState.value = _favState.value.copy(
                        favorites = updatedFavorites,
                        isLoading = false
                    )

                    Log.d("#ABCD", updatedFavorites.toString())
                    println(updatedFavorites)
                }

                is Resource.Error -> {
                    _favState.value = _favState.value.copy(
                        isLoading = false,
                        error = result.message ?: "Unknown error"
                    )
                }

                is Resource.Loading -> TODO()
            }

        }
    }
}
