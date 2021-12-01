package com.resurrection.composemovies.ui.main.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.resurrection.composemovies.ui.main.routeFavoriteScreen

@Composable
fun DetailScreen(navController: NavController, movieId: String,owner: LifecycleOwner) {
    val viewModel: DetailViewModel = hiltViewModel()

    IconButton(
        onClick = { navController.navigate(route = routeFavoriteScreen()) },
        modifier = Modifier.background(color = Color.Cyan)
    ) {

    }

    Text(text = "detail screen")

}
