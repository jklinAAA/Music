package com.example.music.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.example.music.feature.splash.main.mainScreen
import com.example.music.feature.splash.main.navigateToMain
import com.example.music.mediaplayer.musicPlayerScreen
import com.example.music.mediaplayer.navigateToMusicPlayer
import com.example.music.sheetdetail.navigateToSheetDetail
import com.example.music.sheetdetail.sheetDetailScreen
import com.example.music.splash.SPLASH_ROUTE
import com.example.music.splash.splashScreen

@Composable
fun MyApp(navController: NavHostController) {    //应用中的界面要显示需要在这里配置

    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        splashScreen(
            toMain = {
                navController.navigateToMain()
            }
        )
        mainScreen(
            finishPage = navController::popBackStack,
            toSheetDetail = navController::navigateToSheetDetail,
        )

        sheetDetailScreen(
            finishPage = navController::popBackStack,
            toMusicPlayer = navController::navigateToMusicPlayer,
        )

        musicPlayerScreen(
            finishPage = navController::popBackStack,
        )
    }
}