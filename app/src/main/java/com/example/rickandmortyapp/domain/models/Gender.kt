package com.example.rickandmortyapp.domain.models

enum class Gender(val value: String) {
    MALE("Male"),
    FEMALE("Female"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown");

    companion object {
        fun allValues() = values().map { it.value }

        fun fromValue(value: String) = values().find { it.value == value } ?: UNKNOWN
    }
}