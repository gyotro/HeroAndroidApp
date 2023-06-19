package com.example.heroapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.heroapp.data.local.data.HeroDatabase
import com.example.heroapp.data.remote.HeroApi
import com.example.heroapp.data.repository.RemoteDataSourceImpl
import com.example.heroapp.domain.repository.RemoteDataSource
import com.example.heroapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Component.Factory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Class responsible for Retrofit and OkHttp initialization
@Module
@ExperimentalPagingApi
@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
// Creating OkHttpClient object
object NetworkModule {
    private val json: Json = Json { ignoreUnknownKeys = true
                                    prettyPrint = true }

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
            .addConverterFactory(json.asConverterFactory(contentType))
        .build()
    }

    @Provides
    @Singleton
    // providing an instance of HeroApi interface
    fun provideHeroApi(retrofit: Retrofit): HeroApi = retrofit.create(HeroApi::class.java)

    // inseiriamo anche la DI per il RemoteDataSource

    @Provides
    @Singleton
    // providing an instance of HeroApi interface
    fun provideRemoteDataSourceImpl(
        heroApi: HeroApi,
        heroDatabase: HeroDatabase
    ): RemoteDataSource // andrebbe anche bene ritornare RemoteDataSourceImpl
    {
        return RemoteDataSourceImpl(
            heroApi = heroApi,
            heroDatabase = heroDatabase
        )
    }

}