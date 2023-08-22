package com.example.heroapp.domain.use_cases

import com.example.heroapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.heroapp.domain.use_cases.read_AllHeroes.GetAllHeroesUseCase
import com.example.heroapp.domain.use_cases.read_onBoarding.ReadOnBoardingUseCase
import com.example.heroapp.domain.use_cases.save_onBoarding.SaveOnBoardingUseCase
import com.example.heroapp.domain.use_cases.search_heroes.SearchHeroesUseCase

// Use Cases Wrapper Class
data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)
