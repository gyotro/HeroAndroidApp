package com.example.heroapp.data.repository

import com.example.heroapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Repo inerente al data store: ripetere dagger hilt
class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun saveOnBoardingState(complete: Boolean) = dataStoreOperations.saveOnBoardingState(complete)


    fun readOnBoardingState(): Flow<Boolean> = dataStoreOperations.readOnBoardingState()
}