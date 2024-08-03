package com.example.music.feature.splash.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.music.splash.SPLASH_ROUTE
import com.example.music.splash.SplashRoute

const val MAIN_ROUTE = "main"
//跳转界面
fun NavController.navigateToMain(): Unit {
    navigate(MAIN_ROUTE) {
        //不开启多个界面
        launchSingleTop=true
        //关闭splash以及之前所有界面
        popUpTo(SPLASH_ROUTE) {
            inclusive = true
        }
    }
}
//配置导航
fun NavGraphBuilder.mainScreen(
    finishPage: () -> Unit,
) {
    composable(MAIN_ROUTE) {
        MainRoute(
            finishPage = finishPage
        )
    }
}