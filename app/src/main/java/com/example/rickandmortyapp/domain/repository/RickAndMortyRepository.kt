package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.domain.models.Character

class RickAndMortyRepository {

    interface CharacterRepository {

        suspend fun getCharacters(page: String): Resource<List<Character>>

        suspend fun getSingleCharacter(id: String): Resource<Character>
    }
}