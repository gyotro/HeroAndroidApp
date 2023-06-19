package com.example.heroapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.heroapp.data.local.data.HeroDatabase
import com.example.heroapp.data.remote.HeroApi
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.model.HeroRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
// Data will be fetched from the local database and not from the API
class HeroRemoteMediator @Inject constructor(
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase
): RemoteMediator<Int, Hero>() {

    private val heroDao = heroDatabase.heroDao()
    private val heroRemoteKeysDao = heroDatabase.heroRemoteKeysDao()
    // when the app is initialized the HeroRemoteKeys is null

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeysDao.getRemoteKeys(heroId = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }



    // the Load function is a callback function which is triggered every time a user requests more items to be displayed in the APP
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            // per prima cosa chiamiamo l'API
            // depending on the page number we are fetching different Heroes
            val page: Int = when (loadType){
                // refresh may contain content updates, or the initial load.
                // ritorna la pagina attuale
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1 // if null it means that we are refreshing all the items
                }
                // Load at the start of a PagingData.
                // ritorna la pagina precedente alla attuale
                // da ripassare questa casistica
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForTheFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                // Load at the end of a PagingData.
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForTheLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }

            }
            val response = heroApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                heroDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH){
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys: List<HeroRemoteKeys> = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKeys(keys)
                    heroDao.addHero(response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        }catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
    // PagingState contains the information about the pages loaded so far
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id -> // this is the ID of the position, not the id of the Hero
                heroRemoteKeysDao.getRemoteKeys(heroId = id)
            }
        }
    // PagingState contains the information about the pages loaded so far
    private suspend fun getRemoteKeyForTheFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? =
        // prendiamo la lista delle pagine caricate finora, cerchiamo la prima pagina (se non è null), poi prendiamo tutti i dati di quella pagina e prendiamo il primo Id di quei dati
        state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
                hero ->  heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
        }
    // PagingState contains the information about the pages loaded so far
    private suspend  fun getRemoteKeyForTheLastItem(state: PagingState<Int, Hero>):HeroRemoteKeys? =
        state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
                hero ->  heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
        }
}