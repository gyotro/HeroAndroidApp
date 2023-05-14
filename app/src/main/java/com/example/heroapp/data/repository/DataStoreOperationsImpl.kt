package com.example.heroapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.heroapp.data.repository.DataStoreOperationsImpl.PreferencesKey.onBoardingKey
import com.example.heroapp.domain.repository.DataStoreOperations
import com.example.heroapp.util.Constants.DATA_STORE_KEY
import com.example.heroapp.util.Constants.DATA_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// questo oggetto conterrà il nostro Data Store
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {
    // questo sarà il nome della variabile che scriveremo per salvare lo stato della variabile di onboarding
    private object PreferencesKey {
        var onBoardingKey: Preferences.Key<Boolean> = booleanPreferencesKey(name = DATA_STORE_KEY)
    }

    private val dataStore = context.dataStore

    // Settiamo la variabile del datastore a complete (non uscirà più nella seguente esecuzione)
    override suspend fun saveOnBoardingState(complete: Boolean) {
        dataStore.edit { preferences ->
            preferences[onBoardingKey] = complete
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> =
        dataStore.data // datastore.data ritorna un Flow
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else
                    throw exception
            }.map { preferences ->
                return@map preferences[onBoardingKey] ?: false // se non è stato settato, sarà null e quindi ritorna false
            }

}