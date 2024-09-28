package com.example.characters.di

import android.app.Application
import androidx.room.Room
import com.example.characters.common.Constants
import com.example.characters.data.local.CharacterDB
import com.example.characters.data.remote.CharacterApi
import com.example.characters.data.repository.CharacterRepoImpl
import com.example.characters.data.repository.DbRepoImpl
import com.example.characters.domain.repository.CharacterRepository
import com.example.characters.domain.repository.DbRepository
import com.example.characters.domain.usecases.GetCharactersUseCase
import com.example.characters.domain.usecases.SaveCharacter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): CharacterApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

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