package com.example.rickandmortyapp.domain.use_cases

import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.domain.models.Character
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RickAndMortyUseCases @Inject constructor() {

    interface CharacterUseCases {

        fun getCharacters(pageNumber: Int): Flow<Resource<List<Character>>>

        fun getSingleCharacter(id: String): Flow<Resource<Character>>
    }
}