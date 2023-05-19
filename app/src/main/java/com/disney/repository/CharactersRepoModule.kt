package com.disney.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepoModule {

    @Singleton
    @Binds
    abstract fun bindsCharactersRepository(repository: CharactersRepository): ProvideCharacters
}