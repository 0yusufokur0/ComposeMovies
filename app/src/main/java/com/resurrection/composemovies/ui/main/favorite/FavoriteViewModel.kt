package com.resurrection.composemovies.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resurrection.composemovies.data.model.MovieItem
import com.resurrection.composemovies.data.repository.MovieRepository
import com.resurrection.composemovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel@Inject constructor(private val movieRepository: MovieRepository):ViewModel() {
    private val _movie = MutableLiveData<Resource<List<MovieItem>>>()
    private val _movies = MutableLiveData<Resource<List<MovieItem>>>()
    val movie: LiveData<Resource<List<MovieItem>>> = _movie
    val movies: LiveData<Resource<List<MovieItem>>> = _movies

    fun getAllFavoriteMovies()  = viewModelScope.launch{
        movieRepository.getFavoriteMovies()
            .onStart { _movies.postValue(Resource.Loading()) }
            .catch { message -> _movies.postValue(Resource.Error(message)) }
            .collect { it.let { _movies.postValue(it) } }
    }

    fun getMovieByTitle(title: String)  = viewModelScope.launch{
        movieRepository.getMovieByTitle(title)
            .onStart { _movie.postValue(Resource.Loading()) }
            .catch { message -> _movie.postValue(Resource.Error(message)) }
            .collect { it.let { _movie.postValue(it) } }
    }

}