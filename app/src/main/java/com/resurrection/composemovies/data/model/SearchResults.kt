package com.resurrection.movies.data.model

import com.google.gson.annotations.SerializedName
import com.resurrection.composemovies.data.model.MovieItem

data class SearchResults(
    @SerializedName("Response")
    var response: String? = null,

    @SerializedName("totalResults")
    var totalResults: String? = null,

    @SerializedName("Search")
    var movies: List<MovieItem>? = null
)