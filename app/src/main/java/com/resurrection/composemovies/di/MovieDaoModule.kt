package com.resurrection.composemovies.di

import android.content.Context
import androidx.room.Room
import com.resurrection.composemovies.data.db.MovieDatabase
import com.resurrection.composemovies.data.db.dao.MovieDao
import com.resurrection.composemovies.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDaoModule {

    @Provides
    @Singleton
    fun movieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun movieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.movieDao()
}

