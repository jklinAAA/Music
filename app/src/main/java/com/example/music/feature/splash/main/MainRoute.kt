package com.example.music.feature.splash.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.music.design.component.MyNavigationBar
import com.example.music.discovery.DISCOVERY_ROUTE
import com.example.music.discovery.DiscoverRoute
import com.example.music.shortvideo.ShortVideoRoute
import com.example.music.feed.FeedRoute
import com.example.music.me.MeRoute
import com.example.music.ui.theme.SpaceExtraSmallHeight
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    finishPage: () -> Unit
) {
    MainScreen(

    )
}

@OptIn(ExperimentalFoundationApi::class) //实验性api
@Composable
fun MainScreen(
    finishPage: () -> Unit = {}
) {
    //当前选中界面的名称
    var currentDestination by rememberSaveable {
        mutableStateOf(TopLeveDestination.DISCOVERY.route)
    }

    val pagerState = rememberPagerState {
        4
    }

    val  scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,  //禁用手动滚动界面  左右滑动导航
            beyondBoundsPageCount = 4,  //加载屏幕外的更多页面数量
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> DiscoverRoute()
                1 -> ShortVideoRoute()
                2 -> MeRoute()
                3 -> FeedRoute()
            }
        }
        SpaceExtraSmallHeight()
        MyNavigationBar(
            destinations = TopLeveDestination.entries,
            currentDestination = currentDestination,
            onNavigationToDestination = {
                index->
                currentDestination=TopLeveDestination.values()[index].route
           scope.launch {   //协程作用域
               pagerState.scrollToPage(index)
           }

            },
            modifier = Modifier
        )
    }


}





