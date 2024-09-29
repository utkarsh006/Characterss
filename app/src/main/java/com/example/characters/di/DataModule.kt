package com.example.characters.di

import com.example.characters.data.local.CharacterDB
import com.example.characters.data.remote.CharacterApi
import com.example.characters.data.repository.CharacterRepoImpl
import com.example.characters.data.repository.DbRepoImpl
import com.example.characters.domain.repository.CharacterRepository
import com.example.characters.domain.repository.DbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterApi: CharacterApi): CharacterRepository {
        return CharacterRepoImpl(characterApi)
    }

    @Provides
    @Singleton
    fun provideDbRepository(db: CharacterDB): DbRepository {
        return DbRepoImpl(db.characterDao)
    }
}