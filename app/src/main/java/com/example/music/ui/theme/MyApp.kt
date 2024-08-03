package com.example.music.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.music.feature.splash.main.mainScreen
import com.example.music.feature.splash.main.navigateToMain
import com.example.music.splash.SPLASH_ROUTE
import com.example.music.splash.splashScreen

@Composable
fun MyApp(navController: NavHostController) {

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        splashScreen(
            toMain = {
                navController.navigateToMain()
            }
        )
        mainScreen(
            finishPage = navController::popBackStack
        )

    }
}