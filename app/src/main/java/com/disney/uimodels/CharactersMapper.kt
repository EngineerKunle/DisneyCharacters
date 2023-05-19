package com.disney.uimodels

import com.disney.api.data.CharacterDto
import com.disney.api.data.CharactersDto
import javax.inject.Inject

class CharactersMapper @Inject constructor() {

    fun transform(items: CharactersDto):List<Character> = items.characters.map {
        Character(
            it.id,
            it.name,
            it.shortFilms,
            it.parkAttractions,
            calculatePopularity(it)
        )
    }

    private fun calculatePopularity(items: CharacterDto): Int {
        return items.shortFilms.size + items.parkAttractions.size
    }
}