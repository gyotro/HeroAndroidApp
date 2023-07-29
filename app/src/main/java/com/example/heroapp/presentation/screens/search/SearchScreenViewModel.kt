package com.example.heroapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchScreenViewModel: ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchValue(search: String) {
        _searchQuery.value = search
    }

}