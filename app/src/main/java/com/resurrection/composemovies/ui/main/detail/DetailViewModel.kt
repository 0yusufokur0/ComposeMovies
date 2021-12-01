package com.resurrection.composemovies.ui.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resurrection.composemovies.data.model.MovieDetails
import com.resurrection.composemovies.data.model.MovieItem
import com.resurrection.composemovies.data.repository.MovieRepository
import com.resurrection.composemovies.util.Resource
import com.resurrection.composemovies.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository):ViewModel(){

    private var _isFavorite = MutableLiveData<Resource<Boolean>>()
    private val _movieDetail = MutableLiveData<Resource<MovieDetails>>()
    private val _insertMovie = MutableLiveData<Resource<Status>>()
    private val _isRemoved = MutableLiveData<Resource<Boolean>>()
    val insertMovie: LiveData<Resource<Status>> = _insertMovie
    val isRemoved: LiveData<Resource<Boolean>> = _isRemoved
    val movieDetail: LiveData<Resource<MovieDetails>> = _movieDetail
    val isFavorite: LiveData<Resource<Boolean>> = _isFavorite


    fun getMovieDetail(id: String)  = viewModelScope.launch{
        movieRepository.getMovieDetail(id)
            .onStart { _movieDetail.postValue(Resource.Loading()) }
            .catch { message -> _movieDetail.postValue(Resource.Error(message)) }
            .collect {  _movieDetail.postValue(Resource.Success(it.data))  }
    }

    fun insertMovie(movieItem: MovieItem)  = viewModelScope.launch{
        if (movieItem.imdbID.isNotEmpty()){
            movieRepository.insertMovie(movieItem)
                .onStart { _insertMovie.postValue(Resource.Loading()) }
                .catch { message -> _insertMovie.postValue(Resource.Error(message)) }
                .collect { _insertMovie.postValue(Resource.Success(null)) }
        } else  _insertMovie.postValue(Resource.Error(null))
    }

    fun getMovieFavoriteState(id: String)  = viewModelScope.launch{
        if (id.isNotEmpty()){
            movieRepository.getMovieById(id)
                .onStart { _isFavorite.postValue(Resource.Loading()) }
                .catch { message -> _isFavorite.postValue(Resource.Error(message)) }
                .collect {
                    it.data?.let { _isFavorite.postValue(Resource.Success(true)) }
                        ?: run { _isFavorite.postValue(Resource.Success(false)) } }
        }else _isFavorite.postValue(Resource.Error(null))
    }

    fun removeMovie(movieItem: MovieItem)  = viewModelScope.launch{
        movieRepository.removeMovie(movieItem)
            .onStart { _isRemoved.postValue(Resource.Loading()) }
            .catch { message -> _isRemoved.postValue(Resource.Error(message)) }
            .collect {
                it.data?.let { _isRemoved.postValue(Resource.Success(true)) }
                    ?: run { _isRemoved.postValue(Resource.Success(false)) } }
    }
}