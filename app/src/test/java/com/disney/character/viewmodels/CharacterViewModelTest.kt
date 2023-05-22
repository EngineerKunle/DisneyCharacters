package com.disney.character.viewmodels

import com.disney.api.data.CharacterDto
import com.disney.api.data.CharactersDto
import com.disney.character.viewstate.CharactersViewState
import com.disney.uimodels.CharactersMapper
import com.disney.usecase.GetCharacters
import com.disney.veiwmodels.CharactersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private val characters: GetCharacters = mockk(relaxed = true)
    private val mapper: CharactersMapper = mockk(relaxed = true)
    lateinit var viewModel: CharactersViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        viewModel = CharactersViewModel(characters, mapper)
    }

    @Test
    fun `given a success call then view model state should be success`() = runTest {

        val successViewState = CharactersViewState.Success(mapper.transform(createMockedCharacters()))

        coEvery { characters() } returns createMockedCharacters()

        assertEquals(viewModel.charactersState.value, successViewState)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createMockedCharacters() = CharactersDto(
        createCharactersList()
    )

    private fun createCharactersList(): List<CharacterDto> = listOf(
        CharacterDto(1, "A", "image.url", listOf("Film1, Film2"), listOf("sf1, sf2, sf3"), emptyList()),
        CharacterDto(2, "B", "image.url", listOf("Film1, Film2, Film3"), listOf("sf1, sf2"), listOf("P1", "P2")),
        CharacterDto(3, "C", "image.url", listOf("Film1, Film2, Film3"), emptyList(), listOf("P1", "P2", "P3")),
        CharacterDto(4, "D", "image.url", listOf("Film1"), listOf("sf1, sf2, sf3"), listOf("P1", "P2")),
    )
}