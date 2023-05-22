package com.disney.character

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.disney.character.ui.CharactersErrorPage
import com.disney.character.ui.CharactersProgressBar
import com.disney.character.ui.CharactersScreen
import com.disney.character.ui.theme.DisneyCharacterTheme
import com.disney.character.viewstate.CharactersViewState
import com.disney.veiwmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.charactersState.value

            DisneyCharacterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    when (state) {
                        CharactersViewState.Error -> CharactersErrorPage()
                        CharactersViewState.Loading -> CharactersProgressBar()
                        is CharactersViewState.Success -> CharactersScreen(characters = state.data)
                    }
                }
            }
        }
    }
}
