package com.resurrection.composemovies.util

data class Result(
    val success: Int? = null,
    val error: Int? = null,
    val warning: Int? = null,
    val loading: Int? = null
)