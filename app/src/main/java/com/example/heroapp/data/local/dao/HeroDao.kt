package com.example.heroapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.heroapp.model.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    // questa funzione avrebbe potuto rerstituire un Flow, ma noi ora usiamo la paginazione
    @Query("SELECT * FROM hero_table WHERE id = :heroid")
    fun getSelectedHero(heroid: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHero(heroes: List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()
}