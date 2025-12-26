package com.example.characters.di

import com.example.characters.domain.repository.AnimeRepository
import com.example.characters.domain.repository.DbRepository
import com.example.characters.domain.usecases.AllUseCases
import com.example.characters.domain.usecases.FetchCharacters
import com.example.characters.domain.usecases.GetAnimeUseCase
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
        animeRepo: AnimeRepository
    ): AllUseCases {
        return AllUseCases(
            fetchCharacters = FetchCharacters(dbRepository),
            getAnimeUseCase = GetAnimeUseCase(animeRepo),
            removeFavorites = RemoveFavorites(dbRepository),
            saveCharacter = SaveCharacter(dbRepository)
        )
    }
}