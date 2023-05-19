package com.disney.repository

import com.disney.api.data.CharactersDto

interface ProvideCharacters {
    suspend fun getCharacters(): CharactersDto
}