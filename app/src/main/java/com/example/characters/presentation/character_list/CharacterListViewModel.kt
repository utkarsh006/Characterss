package com.example.characters.presentation.character_list

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.common.Resource
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.domain.usecases.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val allUseCases: AllUseCases,
    private val application: Application
) : ViewModel() {

    private val _state = mutableStateOf(CharacterListState())
    val state: State<CharacterListState> get() = _state

    private val _hasNetwork = mutableStateOf(true)
    val hasNetwork: State<Boolean> get() = _hasNetwork

    private val _isRetrying = mutableStateOf(false)
    val isRetrying: State<Boolean> get() = _isRetrying

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private var searchJob: Job? = null
    private var lastSearchedCharacterName: String = ""

    init {
        checkNetworkAndLoadData()
    }

    private fun checkNetworkAndLoadData() {
        if (hasNetwork(application)) {
            getCharacters(lastSearchedCharacterName)
        } else {
            _hasNetwork.value = false
        }
    }

    fun retry() {
        _isRetrying.value = true
        viewModelScope.launch {
            delay(1000)
            _hasNetwork.value = hasNetwork(application) // Check if connected
            if (_hasNetwork.value) {
                getCharacters(lastSearchedCharacterName) // Try fetching characters if connected
            } else {
                _state.value = CharacterListState(error = "Still no internet connection.")
            }
            _isRetrying.value = false // Reset retrying state
        }
    }

    private fun getCharacters(characterName: String) {
        // Cancel the previous search job to avoid unnecessary API calls
        searchJob?.cancel()

        if (characterName.isNotEmpty()) {
            lastSearchedCharacterName = characterName  // Store the last searched character name
            // Delay the API call using debounce
            searchJob = viewModelScope.launch {
                delay(200)
                performSearch(characterName)
            }
        } else {
            // Load all characters if characterName is empty
            loadAllCharacters()
        }
    }

    private fun loadAllCharacters() {
        viewModelScope.launch {
            allUseCases.getCharactersUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CharacterListState(characters = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value =
                            CharacterListState(error = result.message ?: "Unexpected Error")
                    }

                    is Resource.Loading -> {
                        _state.value = CharacterListState(isLoading = true)
                    }
                }
            }.launchIn(this)
        }
    }

    private fun performSearch(characterName: String) {
        if (characterName.isNotEmpty()) {
            allUseCases.getCharactersUseCase.setCityName(characterName)
        }

        allUseCases.getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterListState(characters = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = CharacterListState(error = result.message ?: "Unexpected Error")
                }

                is Resource.Loading -> {
                    _state.value = CharacterListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun hasNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text

        if (text.isEmpty()) {
            lastSearchedCharacterName = ""  // Clear the last searched name
            loadAllCharacters() // Load the full character list when the search is cleared
        } else {
            getCharacters(text)
        }
    }

    fun searchCharacter(characterList: List<CharacterDisplay>): List<CharacterDisplay> {
        return if (searchText.value.isEmpty()) {
            characterList // Return the full list when search text is empty
        } else {
            characterList.filter {
                it.doesMatchSearchQuery(searchText.value) // Filter based on search query
            }
        }
    }

}
