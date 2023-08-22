package com.example.heroapp.domain.repository

import com.example.heroapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}