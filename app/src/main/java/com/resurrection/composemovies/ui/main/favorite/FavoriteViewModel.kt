package com.resurrection.composemovies.ui.main.favorite

import androidx.lifecycle.ViewModel
import com.resurrection.composemovies.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel@Inject constructor(private val movieRepository: MovieRepository):ViewModel() {
}