package com.resurrection.composemovies.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.resurrection.composemovies.ui.main.detail.DetailScreen
import com.resurrection.composemovies.ui.main.favorite.FavoriteScreen
import com.resurrection.composemovies.ui.main.home.HomeScreen
import com.resurrection.composemovies.ui.theme.ComposeMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMoviesTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = routeHomeScreen()) {

                    // HomeScreen Composable
                    composable(route = routeHomeScreen()) { HomeScreen(this@MainActivity,navController,this@MainActivity ) }

                    // FavoriteScreen Composable
                    composable(route = routeFavoriteScreen()) { FavoriteScreen(navController, this@MainActivity) }

                    // DetailScreen Composable
                    composable(
                        route = routeDetailScreen("movieId"),
                        arguments = listOf(navArgument(name = "movieId") { type = NavType.StringType  })
                    ) {
                        val movieId = remember { it.arguments?.getString("movieId") }
                        DetailScreen(navController,movieId ?: "",this@MainActivity)
                    }


                }
            }
        }
    }
}

fun routeDetailScreen(movieId: String) = "detail_screen/{$movieId}"
fun routeHomeScreen() = "home_screen"
fun routeFavoriteScreen() = "favorite_screen"