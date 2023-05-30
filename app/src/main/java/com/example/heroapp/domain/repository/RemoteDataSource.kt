package com.example.heroapp.domain.repository

import androidx.paging.PagingData
import com.example.heroapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHero(): Flow<PagingData<Hero>>
    fun getSearchHero(): Flow<PagingData<Hero>>

}