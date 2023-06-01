package com.example.heroapp.util

object Constants {
    // evitiamo valori hardCoded, usiamo invece i constant object
    const val DETAILS_ARGUMENT_KEY = "heroId"
    const val HERO_DB = "hero_table"
    const val REMOTE_DB_KEYS = "hero_remote_keys_table"
    const val HERO_DB_DEP_INJ = "hero_db"
    const val ON_BOARDING_PAGE_COUNT = 3
    const val DATA_STORE_NAME = "hero_preferences"
    const val DATA_STORE_KEY = "onBoardingComplete"
    // for Android localhost, 10.0.2.2 has to be used
    const val BASE_URL = "http://10.0.2.2:8080"
    const val ITEM_PER_PAGE = 3
}