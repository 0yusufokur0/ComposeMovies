package com.resurrection.composemovies.ui.main.home

import androidx.lifecycle.ViewModel
import com.resurrection.composemovies.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository):ViewModel(){

}