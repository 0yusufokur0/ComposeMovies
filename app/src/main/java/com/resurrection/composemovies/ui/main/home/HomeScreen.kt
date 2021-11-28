package com.resurrection.composemovies.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.resurrection.composemovies.ui.main.detail.DetailScreen
import com.resurrection.composemovies.ui.main.routeDetailScreen

@Composable
fun HomeScreen(navController: NavController) {
    TextButton(
        onClick = { navController.navigate(route = routeDetailScreen("movieId")) },
        //colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red),
        modifier = Modifier.background(Color.Red)
    ) {
        Text(text = "home screen")
    }

}