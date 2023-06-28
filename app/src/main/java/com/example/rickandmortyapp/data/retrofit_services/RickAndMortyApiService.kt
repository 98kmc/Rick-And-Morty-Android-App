package com.example.rickandmortyapp.data.retrofit_services

import com.example.rickandmortyapp.data.api.dto.CharacterDTO
import com.example.rickandmortyapp.domain.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RickAndMortyAPIService {

    interface CharacterService {

        @GET("character/")
        suspend fun fetchCharacters(@Query("page") paramValue: String): CharacterResponse

        @GET("character/{id}")
        suspend fun fetchSingleCharacter(@Path("id") characterId: String): CharacterDTO
    }
}