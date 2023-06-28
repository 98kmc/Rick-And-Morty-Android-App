package com.example.rickandmortyapp.domain.models

import com.example.rickandmortyapp.data.api.dto.CharacterDTO

data class Info(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDTO>
)