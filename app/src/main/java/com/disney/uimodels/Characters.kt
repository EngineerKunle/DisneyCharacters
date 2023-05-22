package com.disney.uimodels

data class Characters(
    val characters: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val parkAttractions: List<String>,
    val popularity: Int,
)