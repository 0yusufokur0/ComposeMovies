package com.resurrection.composemovies.data.repository

import com.resurrection.composemovies.data.model.MovieDetails
import com.resurrection.composemovies.data.model.MovieItem
import com.resurrection.movies.data.model.SearchResults
import com.resurrection.composemovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // Network
    suspend fun getMovieById(id: String, page: Int): Flow<Resource<SearchResults>>
    suspend fun getMovieDetail(imdbId: String): Flow<Resource<MovieDetails>>

    // Database
    suspend fun insertMovie(movie: MovieItem): Flow<Resource<Unit>>
    suspend fun removeMovie(movie: MovieItem): Flow<Resource<Unit>>
    suspend fun getFavoriteMovies(): Flow<Resource<List<MovieItem>>>
    suspend fun getMovieById(imdbID: String): Flow<Resource<MovieItem>>
    suspend fun getMovieByTitle(title: String): Flow<Resource<List<MovieItem>>>
}

