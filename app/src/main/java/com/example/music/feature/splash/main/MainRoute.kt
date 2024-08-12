package com.example.music.feature.splash.main

import android.text.TextUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.music.R
import com.example.music.design.component.MyNavigationBar
import com.example.music.discovery.DISCOVERY_ROUTE
import com.example.music.discovery.DiscoverRoute
import com.example.music.extension.clickableNoRipple
import com.example.music.shortvideo.ShortVideoRoute
import com.example.music.feed.FeedRoute
import com.example.music.me.MeRoute
import com.example.music.model.UserData
import com.example.music.ui.theme.ArrowIcon
import com.example.music.ui.theme.SpaceExtraSmallHeight
import com.example.music.ui.theme.SpaceMediumWidth
import com.example.music.ui.theme.SpaceOuter
import com.example.music.ui.theme.SpacerOuterHeight
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope() //定义协程做作用域  侧滑要在里面使用
    val toggleDrawer: () -> Unit = {  //  打开侧滑的方法
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                MyDrawerView(
                    userData = UserData(),
                    isLogin = false,

                    )
            }
        },
    ) {
        MainScreen(
            toSheetDetail = toSheetDetail,
            toggleDrawer =  toggleDrawer ,
        )
    }


}

@Composable
fun MyDrawerView(
    //侧滑的内容
    userData: UserData,
    isLogin: Boolean,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = SpaceOuter)
    ) {
        SpacerOuterHeight()

        MyUserInfoView(
            userData = userData,
            isLogin = isLogin,
            toProfile = { },
            toLogin = { },
            toScan = { },
        )

        SpacerOuterHeight()
        Column(
            verticalArrangement = Arrangement.spacedBy(SpaceOuter),
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {


        }
    }
}

/**
 * 用户信息
 */
@Composable
fun MyUserInfoView(
    userData: UserData,
    isLogin: Boolean,
    toProfile: () -> Unit,
    toScan: () -> Unit,
    toLogin: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (userData.isLogin()) {

        } else {
            DefaultUserProfile(
                toLogin = toLogin,
                toScan = toScan,
            )
        }
    }
}

private val userIconModifier = Modifier
    .size(30.dp)

@Composable
private fun DefaultUserProfile(
    toLogin: () -> Unit,
    toScan: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoRipple { toLogin() },
    ) {
        Image(
            painter = painterResource(id = R.drawable.mine_pass),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = userIconModifier,
        )

        SpaceMediumWidth()

        Text(
            text = stringResource(id = R.string.login_or_register),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        ArrowIcon()

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = toScan) {
            Icon(
                painter = painterResource(id = R.drawable.scan),
                contentDescription = null,
                modifier = Modifier.size(36.dp),
            )
        }
    }
}

@Composable
private fun UserProfile(
//    data: UserPreferences,
    toProfile: () -> Unit,
    toScan: () -> Unit,
) {

}

@OptIn(ExperimentalFoundationApi::class) //实验性api
@Composable
fun MainScreen(
    finishPage: () -> Unit = {},
    toSheetDetail: (String) -> Unit = {},
    toggleDrawer :() -> Unit ={},

    ) {
    //当前选中界面的名称
    var currentDestination by rememberSaveable {
        mutableStateOf(TopLeveDestination.DISCOVERY.route)
    }

    val pagerState = rememberPagerState {
        4
    }

    val scope = rememberCoroutineScope()

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
                0 -> DiscoverRoute(toSheetDetail = toSheetDetail,
                    toggleDrawer =toggleDrawer,)
                1 -> ShortVideoRoute()
                2 -> MeRoute()
                3 -> FeedRoute()
            }
        }
        SpaceExtraSmallHeight()
        MyNavigationBar(
            destinations = TopLeveDestination.entries,
            currentDestination = currentDestination,
            onNavigationToDestination = { index ->
                currentDestination = TopLeveDestination.values()[index].route
                scope.launch {   //协程作用域
                    pagerState.scrollToPage(index)
                }

            },
            modifier = Modifier
        )
    }


}





