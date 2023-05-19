package com.disney.api

import com.disney.api.data.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DisneyService {
    @GET("character")
    suspend fun getCharacters(
        @Query("pageSize")
        pageSize: Int
    ): CharactersDto
}