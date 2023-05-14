package com.example.heroapp.domain.use_cases.save_onBoarding

import com.example.heroapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repo: Repository
) {
    suspend operator fun invoke(complete: Boolean) = repo.saveOnBoardingState(complete)
}