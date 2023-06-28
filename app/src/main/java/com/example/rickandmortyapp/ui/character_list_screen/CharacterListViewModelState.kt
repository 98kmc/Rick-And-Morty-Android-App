package com.example.rickandmortyapp.ui.character_list_screen

import com.example.rickandmortyapp.domain.models.Character

data class CharacterListViewModelState(
    var characterList: MutableList<Character> = mutableListOf(),
    var error: String = "",
    var isLoading: Boolean = false
)