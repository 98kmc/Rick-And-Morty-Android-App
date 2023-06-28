package com.example.rickandmortyapp.domain.use_cases.impl

import com.example.rickandmortyapp.common.Resource.*
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapp.domain.use_cases.RickAndMortyUseCases
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CharacterUseCasesImpl @Inject constructor(
    private val repository: RickAndMortyRepository.CharacterRepository
) : RickAndMortyUseCases.CharacterUseCases {
    override fun getCharacters(pageNumber: Int) = flow {
        val page = pageNumber.toString()

        try {
            emit(Loading())
            val resource = repository.getCharacters(page)
            emit(resource)
        } catch (error: Exception) {
            emit(Failure(error.message.toString()))
        }
    }

    override fun getSingleCharacter(id: String) = flow {

        try {
            emit(Loading())
            val resource = repository.getSingleCharacter(id)
            emit(resource)
        } catch (error: Exception) {
            emit(Failure(error.message.toString()))
        }
    }
}