package com.example.rickandmortyapp.data.api.dto

import com.example.rickandmortyapp.domain.models.Location
import java.net.URL

data class LocationDTO(
    val name: String?,
    val url: String?
)

fun LocationDTO.toLocationObject() = Location(
    name = this.name ?: "",
    url = runCatching { URL(this.url!!) }.getOrNull()
)