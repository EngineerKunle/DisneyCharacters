package com.disney.character.viewstate

import com.disney.uimodels.Character

sealed class CharactersViewState {
    object Loading : CharactersViewState()
    data class Success(val data: List<Character>) : CharactersViewState()
    object Error : CharactersViewState()
}