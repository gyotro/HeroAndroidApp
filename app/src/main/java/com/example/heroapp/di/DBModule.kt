
package com.example.heroapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.heroapp.util.Constants.HERO_DB_DEP_INJ
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.heroapp.data.local.data.*
import com.example.heroapp.data.repository.LocalDataSourceImpl
import com.example.heroapp.domain.repository.LocalDataSource
import dagger.Provides
import javax.inject.Singleton

// classe responsabile della creazione del DB usande la dependency injection
@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        ): HeroDatabase = Room.databaseBuilder(
        context = context,
        klass = HeroDatabase::class.java,
        name = HERO_DB_DEP_INJ)
            .build()

    @Provides
    @Singleton
    fun provideLocalDB(
        heroDatabase: HeroDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(heroDatabase)
    }

}
