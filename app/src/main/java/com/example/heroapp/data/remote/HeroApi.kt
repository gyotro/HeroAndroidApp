package com.example.heroapp.data.remote

import com.example.heroapp.domain.model.ApiResponse
import com.example.heroapp.domain.model.Hero
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {
    @GET("/anime/heroes")
    suspend fun getAllHeroes(
        @Query("page")
        page: Int = 1
    ): ApiResponse

    @GET("/anime/heroes/search")
    suspend fun searchHero(
        @Query("name")
        name: String
    ): ApiResponse
}