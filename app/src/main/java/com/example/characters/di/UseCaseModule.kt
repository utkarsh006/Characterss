package com.example.characters.di

import com.example.characters.domain.repository.CharacterRepository
import com.example.characters.domain.repository.DbRepository
import com.example.characters.domain.usecases.GetCharactersUseCase
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
    fun provideCharacterUseCase(characterRepo: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepo)
    }

    @Provides
    @Singleton
    fun provideSaveCharacterUseCase(dbRepository: DbRepository): SaveCharacter {
        return SaveCharacter(dbRepository)
    }
}