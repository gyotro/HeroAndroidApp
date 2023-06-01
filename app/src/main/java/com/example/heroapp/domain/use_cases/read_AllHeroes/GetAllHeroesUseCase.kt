package com.example.heroapp.domain.use_cases.read_AllHeroes

import com.example.heroapp.data.repository.Repository

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.getAllHeroes()
}