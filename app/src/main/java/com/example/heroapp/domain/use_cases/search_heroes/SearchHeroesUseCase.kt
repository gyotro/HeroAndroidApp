package com.example.heroapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.example.heroapp.data.repository.Repository
import com.example.heroapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(
        query: String
    ): Flow<PagingData<Hero>> = repository.searchHeroes(query = query)

}