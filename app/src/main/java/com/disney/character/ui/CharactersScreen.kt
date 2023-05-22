package com.disney.character.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.disney.character.R
import com.disney.uimodels.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(characters: List<Character>) {
    val state = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        characters.size
    }

    HorizontalPager(state = state) { page: Int ->
        DisplayCard(character = characters[page])
    }
}

@Composable
private fun DisplayCard(character: Character) {
    val paddingModifier = Modifier
        .padding(10.dp)
        .fillMaxSize()
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), modifier = paddingModifier
    ) {
        DisplayCharacterContent(character)
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun DisplayCharacterContent(character: Character) {
    LazyColumn(Modifier.padding(12.dp, 12.dp)) {
        item {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(top = 10.dp)),
                model = character.id,
                contentDescription = LocalContext.current.getString(R.string.disney_image)
            ) {
                it.load(character.imageUrl)
            }
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(bottom = 8.dp, top = 12.dp)),
                text = character.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(bottom = 8.dp, top = 12.dp)),
                text = stringResource(id = R.string.title_films),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        items(character.films.size) {
            Text(text = character.films[it])
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(bottom = 8.dp, top = 12.dp)),
                text = stringResource(id = R.string.title_short_films),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        items(character.shortFilms.size) {
            Text(text = character.shortFilms[it])
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(bottom = 8.dp, top = 12.dp)),
                text = stringResource(id = R.string.title_park_attractions),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        items(character.parkAttractions.size) {
            Text(text = character.parkAttractions[it])
        }
    }
}