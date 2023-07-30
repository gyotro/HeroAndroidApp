package com.example.heroapp.di

import android.content.Context
import com.example.heroapp.data.repository.DataStoreOperationsImpl
import com.example.heroapp.data.repository.Repository
import com.example.heroapp.domain.repository.DataStoreOperations
import com.example.heroapp.domain.use_cases.UseCases
import com.example.heroapp.domain.use_cases.read_AllHeroes.GetAllHeroesUseCase
import com.example.heroapp.domain.use_cases.read_onBoarding.ReadOnBoardingUseCase
import com.example.heroapp.domain.use_cases.save_onBoarding.SaveOnBoardingUseCase
import com.example.heroapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository)
        )
    }



}