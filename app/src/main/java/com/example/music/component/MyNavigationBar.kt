package com.example.music.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.music.extension.clickableNoRipple
import com.example.music.feature.splash.main.TopLeveDestination
import com.example.music.ui.theme.SpaceExtraMedium
import com.example.music.ui.theme.SpaceSmallHeight

//底部导航
@Composable
fun MyNavigationBar(
    destinations: List<TopLeveDestination>,
    currentDestination: String,
    onNavigationToDestination: (Int) -> Unit,
    modifier: Modifier = Modifier,
): Unit {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .navigationBarsPadding()   //手机底部控制栏适配  不然会重合
    ) {
        destinations.forEachIndexed { index, destination ->

            val selected = destination.route == currentDestination

            val color = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = SpaceExtraMedium)
                    .clickableNoRipple { onNavigationToDestination(index) }  //点击切换导航   如果是clickable是有水波效果 想换就换

            ) {
                Image(
                    painter = painterResource( //选中和未选中的图标显示
                        id =
                        if (selected)
                            destination.selectedIcon
                        else destination.unselectedIcon
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                SpaceSmallHeight()
                Text(
                    text = stringResource(id = destination.titleTextId), color = color
                )
            }
        }
    }
}