package com.example.rickandmortyapp.ui.character_list_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.common.Constants.LAST_PAGE_NUMBER
import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.domain.use_cases.RickAndMortyUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.example.rickandmortyapp.domain.models.Character

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCases: RickAndMortyUseCases.CharacterUseCases
) : ViewModel() {

    val TAG = "CharacterListViewModel"

    var state = MutableStateFlow(CharacterListViewModelState())
        private set

    private var currentPage = 1

    // Public_Methods
    fun recyclerDidScrollUntilBottom() {
        loadNextPage()
    }

    fun recyclerDidMadeRefreshGesture() {
        refresh()
    }

    // Private_Methods
    private fun refresh() {
        viewModelScope.launch {
            currentPage = 1
            val characterList = getCharacters(currentPage)
            state.value = CharacterListViewModelState(characterList.toMutableList())
        }
    }

    private fun loadNextPage() {

        currentPage++
        if (currentPage <= LAST_PAGE_NUMBER) {

            viewModelScope.launch {
                val currentList = state.value.characterList
                val characterList = getCharacters(currentPage)
                state.value = CharacterListViewModelState((currentList+ characterList).toMutableList())
                Log.d(TAG, "recyclerDidScrollUntilBottom: ${state.value.characterList.count()}")
            }
        }
    }

    private suspend fun getCharacters(page: Int): List<Character> = suspendCoroutine { continuation ->
        var characterList: List<Character> = emptyList()

        characterUseCases.getCharacters(page).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    characterList = resource.data ?: emptyList()
                }

                is Resource.Failure ->
                    state.value = CharacterListViewModelState(error = resource.message.toString())

                is Resource.Loading ->
                    state.value = CharacterListViewModelState(isLoading = true)
            }

            if (resource is Resource.Success || resource is Resource.Failure) {
                continuation.resume(characterList)
            }
        }.launchIn(viewModelScope)
    }
}