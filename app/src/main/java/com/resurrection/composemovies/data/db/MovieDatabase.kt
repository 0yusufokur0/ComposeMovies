package com.resurrection.composemovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.resurrection.composemovies.data.db.dao.MovieDao
import com.resurrection.composemovies.data.model.MovieItem

@Database(
    entities = [MovieItem::class],
    version = 1
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
