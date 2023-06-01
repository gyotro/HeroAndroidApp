package com.example.heroapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.example.heroapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    // sarebbe la stessa cosa fare useCases.getAllHeroesUseCase.invoke()
    val allHeroes = useCases.getAllHeroesUseCase()
}
