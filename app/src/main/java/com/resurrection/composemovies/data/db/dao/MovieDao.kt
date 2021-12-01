package com.resurrection.composemovies.data.db.dao

import androidx.room.*
import com.resurrection.composemovies.data.model.MovieItem

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieItem)

    @Delete
    suspend fun removeMovie(movie: MovieItem)

    @Query("SELECT * FROM search_item")
    suspend fun getFavoriteMovies(): List<MovieItem>

    @Query("SELECT * FROM search_item where  imdbId like :imdbID")
    suspend fun getMovieById(imdbID: String): MovieItem

    @Query("SELECT * FROM search_item WHERE title LIKE '%' || :title || '%' OR imdbID LIKE '%' || :title || '%'")
    suspend fun getMovieByTitle(title: String): List<MovieItem>
}

