package com.disney.repository

import com.disney.api.DisneyService
import com.disney.api.data.CharactersDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val disneyService: DisneyService,
) : ProvideCharacters {

    override suspend fun getCharacters(): CharactersDto {
        return withContext(Dispatchers.IO) {
            disneyService.getCharacters(pageSize = 100)
        }
    }
}