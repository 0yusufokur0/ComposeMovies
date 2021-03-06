package com.resurrection.composemovies.ui.main.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resurrection.composemovies.data.repository.MovieRepository
import com.resurrection.composemovies.util.Resource
import com.resurrection.movies.data.model.SearchResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository):ViewModel(){

    init {
        //getMovie("Turkey")
    }

    private val _movie = MutableLiveData<Resource<SearchResults>>()
    val movie: LiveData<Resource<SearchResults>> = _movie

    var testMovie = mutableStateOf<Resource<SearchResults>>(Resource.Loading())

    fun getMovie(id: String) = viewModelScope.launch {
        movieRepository.getMovieById(id, 1)
            .onStart { _movie.postValue(Resource.Loading()) }
            .catch { message -> _movie.postValue(Resource.Error(message)) }
            .collect { _movie.postValue(it)  }


/*        movieRepository.getMovieById(id, 1)
            .onStart { testMovie.value = Resource.Loading() }
            .catch { message -> testMovie.value = (Resource.Error(message)) }
            .collect { testMovie.value = (it)  }*/
    }
}