package com.disney.usecase

import com.disney.repository.CharactersRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke() = repository.getCharacters()
}