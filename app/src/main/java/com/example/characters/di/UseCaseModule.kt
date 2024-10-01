package com.example.characters.di

import com.example.characters.domain.repository.CharacterRepository
import com.example.characters.domain.repository.DbRepository
import com.example.characters.domain.usecases.AllUseCases
import com.example.characters.domain.usecases.FetchCharacters
import com.example.characters.domain.usecases.GetCharactersUseCase
import com.example.characters.domain.usecases.RemoveFavorites
import com.example.characters.domain.usecases.SaveCharacter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAllUseCases(
        dbRepository: DbRepository,
        characterRepo: CharacterRepository
    ): AllUseCases {
        return AllUseCases(
            fetchCharacters = FetchCharacters(dbRepository),
            getCharactersUseCase = GetCharactersUseCase(characterRepo),
            removeFavorites = RemoveFavorites(dbRepository),
            saveCharacter = SaveCharacter(dbRepository)
        )
    }
}