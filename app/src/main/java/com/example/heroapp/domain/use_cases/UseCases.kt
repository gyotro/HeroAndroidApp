package com.example.heroapp.domain.use_cases

import com.example.heroapp.domain.use_cases.read_AllHeroes.GetAllHeroesUseCase
import com.example.heroapp.domain.use_cases.read_onBoarding.ReadOnBoardingUseCase
import com.example.heroapp.domain.use_cases.save_onBoarding.SaveOnBoardingUseCase

// Use Cases Wrapper Class
data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)
