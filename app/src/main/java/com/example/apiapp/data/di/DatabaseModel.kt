package com.example.apiapp.data.di

import android.content.Context
import com.example.apiapp.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return MovieDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()

}
