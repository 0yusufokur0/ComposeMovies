package com.resurrection.composemovies.ui.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import com.resurrection.composemovies.data.model.MovieItem
import com.resurrection.composemovies.util.Resource
import com.resurrection.composemovies.util.Status
import com.resurrection.movies.data.model.SearchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController, owner: LifecycleOwner) {
    val viewModel: HomeViewModel = hiltViewModel()

    var movies = remember { mutableStateListOf<MovieItem>() }
    viewModel.getMovie("Turkey")
    val currentUser: Resource<SearchResults>? by viewModel.movie.observeAsState(null)

    when(currentUser?.status){
        Status.SUCCESS->{
            movies.addAll(currentUser!!.data?.movies!!)
        }
    }


    MovieListView(movies = movies)
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun MovieItem(movieItem: MovieItem) {
/*    Image(painter = rememberImagePainter(data = movieItem.poster),
        contentDescription = "")*/
    Text(text = "Welcome, ${movieItem.imdbID}")
}


@ExperimentalFoundationApi
@Composable
fun MovieListView(movies: List<MovieItem>) {

    LazyColumn {
        itemsIndexed(movies) { index, item ->

            MovieItem(movieItem = item )
        }
    }
}


