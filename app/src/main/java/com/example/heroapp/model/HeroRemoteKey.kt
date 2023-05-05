package com.example.heroapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.heroapp.util.Constants.REMOTE_DB_KEY

@Entity(tableName = REMOTE_DB_KEY)
// questa classe servirà per la paginazione
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)