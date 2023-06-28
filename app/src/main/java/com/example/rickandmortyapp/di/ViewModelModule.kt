package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository.CharacterRepository
import com.example.rickandmortyapp.domain.use_cases.impl.CharacterUseCasesImpl
import com.example.rickandmortyapp.domain.use_cases.RickAndMortyUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCharacterUseCases(repository: CharacterRepository): RickAndMortyUseCases.CharacterUseCases
            = CharacterUseCasesImpl(repository)
}