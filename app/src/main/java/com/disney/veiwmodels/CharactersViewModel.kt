package com.disney.veiwmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disney.character.viewstate.CharactersViewState
import com.disney.character.viewstate.CharactersViewState.Loading
import com.disney.uimodels.Character
import com.disney.uimodels.CharactersMapper
import com.disney.usecase.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val mapper: CharactersMapper
) : ViewModel() {

    val charactersState: MutableState<CharactersViewState> = mutableStateOf(Loading)

    init {
        loadDisneyCharacters()
    }

    private fun loadDisneyCharacters() {
        viewModelScope.launch {
            try {
                getCharacters().let { characters ->
                    val mappedItems:List<Character> = mapper.transform(characters).sortedByDescending { it.popularity }
                    charactersState.value = CharactersViewState.Success(mappedItems)
                }
            } catch (e: Error) {
                charactersState.value = CharactersViewState.Error
            }
        }

    }
}