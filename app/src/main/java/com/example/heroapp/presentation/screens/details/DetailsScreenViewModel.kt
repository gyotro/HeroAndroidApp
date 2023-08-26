package com.example.heroapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.use_cases.UseCases
import com.example.heroapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.heroapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCase: UseCases,
    // serve per prendere il valore di HeroID (approfondire)
    savedStateHandle: SavedStateHandle
): ViewModel() {

    // è necessario usare il MutableStateFlow perché nella coroutine per prendere i valori si sta usando il Dispatchers.IO. si usa Dispatchers.IO e non Dispatchers.Main perché dobbiamo leggere dal DB
    private val _heroDetail = MutableStateFlow<Hero?>(null)
    val heroDetail: StateFlow<Hero?> = _heroDetail

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(Constants.DETAILS_ARGUMENT_KEY)
            // se avessimo usato il by, non avremmo usato lo State, ma avremmo direttamente il valore
            _heroDetail.value = heroId?.let { useCase.getSelectedHeroUseCase(it) }
            // Logging Hero Name
            _heroDetail.value?.name.let {
                Log.d("Hero", "HeroName: $it")
            }
        }
    }
}