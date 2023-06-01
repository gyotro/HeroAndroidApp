package com.example.heroapp.data.local.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.heroapp.domain.model.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero_table ORDER BY id ASC")
    // se si inserisce suspend sulle get con il Paging, va in errore di compilazione
    fun getAllHeroes(): PagingSource<Int, Hero>

    // questa funzione avrebbe potuto rerstituire un Flow, ma noi ora usiamo la paginazione
    @Query("SELECT * FROM hero_table WHERE id = :heroId")
    suspend fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHero(heroes: List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()
}