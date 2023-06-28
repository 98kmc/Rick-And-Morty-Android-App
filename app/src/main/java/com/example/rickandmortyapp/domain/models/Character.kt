package com.example.rickandmortyapp.domain.models

import java.net.URL

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: URL?,
    val episode: List<String>,
    val url: URL?,
    val created: String
)