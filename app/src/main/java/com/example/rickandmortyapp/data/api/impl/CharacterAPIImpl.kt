package com.example.rickandmortyapp.data.api.impl

import com.example.rickandmortyapp.data.api.RickAndMortyAPI
import com.example.rickandmortyapp.data.api.dto.toCharacterObject
import com.example.rickandmortyapp.data.retrofit_services.RickAndMortyAPIService
import com.example.rickandmortyapp.domain.models.Character
import retrofit2.Retrofit
import javax.inject.Inject

class RickAndMortyCharacterAPIImpl @Inject constructor(
    retrofit: Retrofit
) : RickAndMortyAPI.RickAndMortyCharacterAPI {

    private val api = retrofit.create(RickAndMortyAPIService.CharacterService::class.java)

    override suspend fun fetchCharacters(page: String): List<Character> {

        val characterResponse = api.fetchCharacters(page)
        return characterResponse.results.map { it.toCharacterObject() }
    }

    override suspend fun fetchSingleCharacter(id: String): Character {

        val response = api.fetchSingleCharacter(id)
        return response.toCharacterObject()
    }
}