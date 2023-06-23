package com.example.heroapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.heroapp.data.local.data.HeroDatabase
import com.example.heroapp.data.paging_source.HeroRemoteMediator
import com.example.heroapp.data.remote.HeroApi
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.repository.RemoteDataSource
import com.example.heroapp.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase
): RemoteDataSource {
    private val heroDao = heroDatabase.heroDao()

    override fun getAllHero(): Flow<PagingData<Hero>> {
        // richiamiamo la nostra getAllHeroes che restituisce una paging result
        // questa sarà la nostra single source of truth
        // pagingSourcefactory (che restituisce la paging Source) deve essere una lambda, altrimenti
        // avremo un errore di istanze nella creazione della classe PagingSource
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                heroApi = heroApi,
                heroDatabase = heroDatabase
            ),
            pagingSourceFactory =  pagingSourceFactory
        ).flow
    }

    override fun getSearchHero(): Flow<PagingData<Hero>> {
        TODO()
    }
}