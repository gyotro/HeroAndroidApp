package com.example.heroapp.domain.use_cases.get_selected_hero

import com.example.heroapp.data.repository.Repository

class GetSelectedHeroUseCase(private val repository: Repository) {
    suspend operator fun invoke(heroId: Int) = repository.getSelectedHero(heroId)
}