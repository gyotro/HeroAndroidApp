package com.example.heroapp.domain.repository

import kotlinx.coroutines.flow.Flow

// con il DataStore si immagazzinano delle variabili utilizzate a runtime dalla nostra app
// ti puoi passare valori da una esecuzione all'altra
interface DataStoreOperations {
    suspend fun saveOnBoardingState(complete: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}