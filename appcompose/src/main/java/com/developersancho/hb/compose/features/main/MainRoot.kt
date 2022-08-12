package com.developersancho.hb.compose.features.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.developersancho.hb.compose.app.extensions.SetupSystemUi
import com.developersancho.hb.compose.app.theme.HBCaseTheme
import com.developersancho.hb.compose.features.NavGraphs
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@Composable
fun MainRoot(finish: () -> Unit) {
    val engine = rememberAnimatedNavHostEngine()
    val navController = engine.rememberNavController()
    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: NavGraphs.root.startRoute.route

    if (destination == NavGraphs.root.startRoute.route) {
        BackHandler { finish() }
    }

    HBCaseTheme {
        SetupSystemUi(rememberSystemUiController(), MaterialTheme.colors.primary)
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DestinationsNavHost(
                engine = engine,
                navController = navController,
                navGraph = NavGraphs.root
            )
        }
    }
}