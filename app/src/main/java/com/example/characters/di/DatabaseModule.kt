package com.example.characters.di

import android.app.Application
import androidx.room.Room
import com.example.characters.data.local.CharacterDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(app: Application): CharacterDB {
        return Room.databaseBuilder(
            app,
            CharacterDB::class.java,
            CharacterDB.DB_NAME
        ).build()
    }
}