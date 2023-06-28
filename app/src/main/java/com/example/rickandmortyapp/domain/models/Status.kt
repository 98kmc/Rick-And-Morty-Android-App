package com.example.rickandmortyapp.domain.models

enum class Status(val value: String) {

    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    companion object {
        fun allValues() = values().map { it.value }

        fun fromValue(value: String) = values().find { it.value == value } ?: UNKNOWN
    }
}