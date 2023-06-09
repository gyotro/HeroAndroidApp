package com.example.heroapp.data.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.heroapp.data.local.DataBaseConverter
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.domain.model.HeroRemoteKeys

// con questa classe dichiariamo le entity da creare in base alle classi già create in precedenza
@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(DataBaseConverter::class)
abstract class HeroDatabase: RoomDatabase()  {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}