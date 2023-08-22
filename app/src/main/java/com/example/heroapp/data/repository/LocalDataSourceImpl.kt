package com.example.heroapp.data.repository

import com.example.heroapp.data.local.data.HeroDatabase
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(heroDatabase: HeroDatabase): LocalDataSource {

    private val heroDao = heroDatabase.heroDao()
    override suspend fun getSelectedHero(heroId: Int) = heroDao.getSelectedHero(heroId)
}