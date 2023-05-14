package com.example.heroapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.heroapp.util.Constants.HERO_DB

@Entity(tableName = HERO_DB)
data class Hero(
    @PrimaryKey(autoGenerate = false) // noi già abbiamo l'Id
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)
