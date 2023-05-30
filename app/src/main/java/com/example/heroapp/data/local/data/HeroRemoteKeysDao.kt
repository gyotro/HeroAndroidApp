package com.example.heroapp.data.local.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.heroapp.domain.model.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {
    @Query("SELECT * FROM hero_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(heroId: Int): HeroRemoteKeys?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<HeroRemoteKeys>)
    @Query("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}