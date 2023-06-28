package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.common.Constants.BASE_URL
import com.example.rickandmortyapp.data.api.RickAndMortyAPI
import com.example.rickandmortyapp.data.api.impl.RickAndMortyCharacterAPIImpl
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapp.domain.repository.impl.CharacterRepositoryImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): RickAndMortyAPI.RickAndMortyCharacterAPI
            = RickAndMortyCharacterAPIImpl(retrofit)

    @Provides
    @Singleton
    fun provideCharacterRepository(apiManager: RickAndMortyAPI.RickAndMortyCharacterAPI): RickAndMortyRepository.CharacterRepository
            = CharacterRepositoryImpl(apiManager)
}