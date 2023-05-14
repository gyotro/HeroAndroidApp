package com.example.heroapp.domain.use_cases.read_onBoarding

import com.example.heroapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repo: Repository
) {
    // operator function vuol dire che richiamo l'operazione semplicemente richiamando la classe
    operator fun invoke(): Flow<Boolean> = repo.readOnBoardingState()
}