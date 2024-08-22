package com.example.characters.di

import com.example.characters.common.Constants
import com.example.characters.data.remote.CharacterApi
import com.example.characters.data.repository.CharacterRepoImpl
import com.example.characters.domain.repository.CharacterRepository
import com.example.characters.domain.usecases.GetCharactersUseCase
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

    //This function we would provide to our repository
    @Provides
    @Singleton
    fun provideCharacterRepository(characterApi: CharacterApi): CharacterRepository {
        return CharacterRepoImpl(characterApi)
    }

    @Provides
    @Singleton
    fun provideCharacterUseCase(characterRepo: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepo)
    }
}