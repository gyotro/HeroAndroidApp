package com.example.heroapp.data.repository

import androidx.paging.PagingData
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.repository.DataStoreOperations
import com.example.heroapp.domain.repository.LocalDataSource
import com.example.heroapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Repo inerente al data store ed al DataSource
class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun saveOnBoardingState(complete: Boolean) = dataStoreOperations.saveOnBoardingState(complete)

    fun getAllHeroes(): Flow<PagingData<Hero>> = remoteDataSource.getAllHero()
    fun readOnBoardingState(): Flow<Boolean> = dataStoreOperations.readOnBoardingState()

    suspend fun getSelectedHero(heroId: Int) = localDataSource.getSelectedHero(heroId)

    fun searchHeroes(query: String): Flow<PagingData<Hero>> = remoteDataSource.getSearchHero(query = query)
}