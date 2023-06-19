package com.example.heroapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.heroapp.util.Constants.REMOTE_DB_KEYS

@Entity(tableName = REMOTE_DB_KEYS)
// questa classe servirà per la paginazione
// nel nostro caso le Keys saranno 2: prev pag e next page
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)