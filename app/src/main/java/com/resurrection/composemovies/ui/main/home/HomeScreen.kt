package com.resurrection.composemovies.ui.main.home

import android.content.Context
import android.util.DisplayMetrics
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.resurrection.composemovies.data.model.MovieItem
import com.resurrection.composemovies.util.Resource
import com.resurrection.composemovies.util.Status
import com.resurrection.movies.data.model.SearchResults

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(context: Context, navController: NavController, owner: LifecycleOwner) {
    val viewModel: HomeViewModel = hiltViewModel()
    var movies = remember { mutableStateListOf<MovieItem>() }
    viewModel.getMovie("What")
    val currentUser: Resource<SearchResults>? by viewModel.movie.observeAsState(null)

    Surface(modifier = Modifier.background(color = Color.Black)) {
        MovieListView(context = context, movies = movies)
    }

    when (currentUser?.status) {
        Status.SUCCESS -> {
            movies.addAll(currentUser!!.data?.movies!!)
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun MovieItem(context: Context, movieItem: MovieItem) {

    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density

    val cardheight = (dpHeight / 3)
    val cardwidth = (dpWidth - 20)


    val movieItemImagePainter = rememberImagePainter(
        data = movieItem.poster,
        builder = { transformations(RoundedCornersTransformation(15f)) }
    )
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height((dpHeight / 3).dp),
        shape = RoundedCornerShape(15.dp),

        ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = movieItemImagePainter,
                contentDescription = null,
                modifier = Modifier
                    .width(((cardwidth / 3) + 10).dp)
                    .height(((cardheight / 1.5)).dp)
            )

            Text(text = movieItem.title.toString(), textAlign = TextAlign.Center)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(text = movieItem.type.toString())
                Text(text = movieItem.year.toString())
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MovieListView(context: Context, movies: List<MovieItem>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .background(color = Color.Black)
    ) {
        itemsIndexed(movies) { index, item ->
            MovieItem(context, movieItem = item)
        }
    }
}


