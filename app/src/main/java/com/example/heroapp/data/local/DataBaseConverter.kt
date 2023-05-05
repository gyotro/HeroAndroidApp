package com.example.heroapp.data.local

import androidx.room.TypeConverter

// classe utilizzata da Room per convertire i tipi non nativi di SQL (tipo le liste di String)
class DataBaseConverter {
    private val separator = ","
    @TypeConverter
    fun convertListToString(list: List<String>): String = list.joinToString(separator)
    @TypeConverter
    fun convertStringToList(string: String): List<String> = string.split(separator)
}