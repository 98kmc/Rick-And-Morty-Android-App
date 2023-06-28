package com.example.rickandmortyapp.domain.repository.impl

import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.data.api.RickAndMortyAPI
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiManager: RickAndMortyAPI.RickAndMortyCharacterAPI
): RickAndMortyRepository.CharacterRepository {

    override suspend fun getCharacters(page: String): Resource<List<Character>> {
        val characterList = apiManager.fetchCharacters(page)
        return Resource.Success(characterList)
    }

    override suspend fun getSingleCharacter(id: String): Resource<Character> {
        val character = apiManager.fetchSingleCharacter(id)
        return Resource.Success(character)
    }
}