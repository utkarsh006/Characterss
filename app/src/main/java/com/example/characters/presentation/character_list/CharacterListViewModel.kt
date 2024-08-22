package com.example.characters.presentation.character_list

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.common.Resource
import com.example.characters.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val application: Application
) : ViewModel() {

    private val _state = mutableStateOf(CharacterListState())
    val state: State<CharacterListState> get() = _state

    private val _hasNetwork = mutableStateOf(true)
    val hasNetwork: State<Boolean> get() = _hasNetwork

    private val _isRetrying = mutableStateOf(false)
    val isRetrying: State<Boolean> get() = _isRetrying

    init {
        checkNetworkAndLoadData()
    }

    private fun checkNetworkAndLoadData() {
        if (hasNetwork(application)) {
            getCharacters()
        } else {
            _hasNetwork.value = false
        }
    }

    fun retry() {
        _isRetrying.value = true
        // Simulate network check and data loading
        viewModelScope.launch {
            delay(1000) // Simulating a delay for network request
            _hasNetwork.value = hasNetwork(application)
            if (_hasNetwork.value) {
                getCharacters()
            }
            _isRetrying.value = false
        }
    }

    private fun getCharacters() {
        getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterListState(meals = result.data ?: emptyList())
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
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}