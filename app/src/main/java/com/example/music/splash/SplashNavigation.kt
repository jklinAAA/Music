package com.example.music.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

const val SPLASH_ROUTE = "splash"


fun NavGraphBuilder.splashScreen(toMain:()->Unit,) {
    composable(SPLASH_ROUTE) {
        SplashRoute(
          toMain = toMain

        )
    }
}