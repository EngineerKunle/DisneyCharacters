package com.disney.api.data

import com.google.gson.annotations.SerializedName

data class CharactersDto(
    @SerializedName("data")
    val characters: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val parkAttractions: List<String>,
)