package com.resurrection.composemovies.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.resurrection.composemovies.ui.main.home.HomeScreen
import com.resurrection.composemovies.ui.theme.ComposeMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMoviesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home_screen"){
                    // HomeScreen Composable
                    composable(route = "home_screen"){ HomeScreen(navController = navController)}

                    // DetailScreen Composable


                }
            }
        }
    }
}

