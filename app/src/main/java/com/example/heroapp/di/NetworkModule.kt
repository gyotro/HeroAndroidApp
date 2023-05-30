package com.example.heroapp.di

import com.example.heroapp.data.remote.HeroApi
import com.example.heroapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Class responsible for Retrofit and OkHttp initialization
@Module
@InstallIn(SingletonComponent::class)
// Creating OkHttpClient object
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()

    // Creating Retrofit Client
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
    }

    @Provides
    @Singleton
    // providing an instance of HeroApi interface
    fun provideHeroApi(retrofit: Retrofit): HeroApi = retrofit.create(HeroApi::class.java)

}