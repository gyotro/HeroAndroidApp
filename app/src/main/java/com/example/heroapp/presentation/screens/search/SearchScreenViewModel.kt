package com.example.heroapp.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.temporal.TemporalQuery
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _searchQuery by mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedHeroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchedHeroes = _searchedHeroes

    fun updateSearchValue(search: String) {
        _searchQuery = search
    }

    fun searchHero(
        query: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        useCases.searchHeroesUseCase(query = query).cachedIn(viewModelScope).collect{
            _searchedHeroes.value = it
        }
    }

}