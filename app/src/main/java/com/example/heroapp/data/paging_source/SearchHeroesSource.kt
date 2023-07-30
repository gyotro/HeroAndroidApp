package com.example.heroapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.heroapp.data.remote.HeroApi
import com.example.heroapp.domain.model.Hero
import javax.inject.Inject

// va implementata la classe Paging Source, xke da room DB non ce nè bisogno, ma da API si
class SearchHeroesSource @Inject constructor(
    private val heroApi: HeroApi, // Retrofit Interface
    private val query: String
): PagingSource<Int, Hero>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Hero> = try
    {
        val apiResponse = heroApi.searchHero(query)
        val heroes = apiResponse.heroes
        if (heroes.isNotEmpty())
        {
            LoadResult.Page(
                data = heroes,
                prevKey = apiResponse.prevPage,
                nextKey = apiResponse.nextPage
            )
        }
        else
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
    }catch (e: Exception) {
        LoadResult.Error(e)
    }

    /*
    da documentazione leggiamo:
    The last accessed position can be retrieved via state.anchorPosition,
    which is typically the top-most or bottom-most item in the viewport due to access being triggered by binding items as they scroll into view.
     */
    override fun getRefreshKey(state: PagingState<Int, Hero>) = state.anchorPosition

}