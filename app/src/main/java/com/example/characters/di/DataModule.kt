package com.example.characters.di

import com.example.characters.data.local.CharacterDB
import com.example.characters.data.remote.AnimeApi
import com.example.characters.data.repository.AnimeRepoImpl
import com.example.characters.data.repository.DbRepoImpl
import com.example.characters.domain.repository.AnimeRepository
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
    fun provideAnimeRepository(animeApi: AnimeApi, db: CharacterDB): AnimeRepository {
        return AnimeRepoImpl(animeApi, db.characterDao)
    }

    @Provides
    @Singleton
    fun provideDbRepository(db: CharacterDB): DbRepository {
        return DbRepoImpl(db.characterDao)
    }
}