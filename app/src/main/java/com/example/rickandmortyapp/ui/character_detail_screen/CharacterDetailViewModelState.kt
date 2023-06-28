package com.example.rickandmortyapp.ui.character_detail_screen

import com.example.rickandmortyapp.domain.models.Character

data class CharacterDetailViewModelState(
    var character: Character? = null,
    var isLoading: Boolean = false,
    var error: String? = null
)
