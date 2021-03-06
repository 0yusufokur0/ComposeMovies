package com.resurrection.composemovies.di

import com.resurrection.composemovies.data.repository.MovieRepository
import com.resurrection.composemovies.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(repository: MovieRepositoryImpl): MovieRepository
}