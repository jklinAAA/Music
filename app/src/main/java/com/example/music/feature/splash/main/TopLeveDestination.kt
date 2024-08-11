package com.example.music.feature.splash.main

import com.example.music.R
import com.example.music.discovery.DISCOVERY_ROUTE
import com.example.music.feed.FEED_ROUTE
import com.example.music.me.ME_ROUTE
import com.example.music.shortvideo.SHORT_VIDEO_ROUTE

enum class TopLeveDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val titleTextId: Int,
    val route: String,

    ) {
    //发现界面
    DISCOVERY(
        selectedIcon = R.drawable.shouye_click,   //选择的图片
        unselectedIcon = R.drawable.shouye_pass, //未选择的图标
        titleTextId = R.string.home,
        route = DISCOVERY_ROUTE,
    ),
    SHORT_VIDEO(
        selectedIcon = R.drawable.find_click,
        unselectedIcon = R.drawable.find_pass,
        titleTextId = R.string.video,
        route = SHORT_VIDEO_ROUTE,
    ),
    ME(
        selectedIcon = R.drawable.mine_click,
        unselectedIcon = R.drawable.mine_pass,
        titleTextId = R.string.me,
        route = ME_ROUTE,
    ),
    FEED(
        selectedIcon = R.drawable.dongtai_click,
        unselectedIcon = R.drawable.dongtai_pass,
        titleTextId = R.string.feed,
        route = FEED_ROUTE,
    )
}