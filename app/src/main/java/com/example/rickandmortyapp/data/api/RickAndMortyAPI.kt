package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.domain.models.Character

class RickAndMortyAPI {

    interface RickAndMortyCharacterAPI {

        suspend fun fetchCharacters(page: String): List<Character>

        suspend fun fetchSingleCharacter(id: String): Character
    }
}