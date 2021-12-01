package com.resurrection.composemovies.ui.main.favorite

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.resurrection.composemovies.ui.main.detail.DetailViewModel

@Composable
fun FavoriteScreen(navController: NavController,owner: LifecycleOwner) {
    val viewModel: DetailViewModel = hiltViewModel()

    Text(text = "favorite screen")
}