package com.example.music.discovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.music.R
import com.example.music.model.Song
import com.example.music.model.ViewData
import com.example.music.sheet.ItemSheet
import com.example.music.song.component.ItemSong
import com.example.music.ui.theme.DiscoveryPreviewParameterData.SONGS
import com.example.music.ui.theme.DiscoveryPreviewParameterProvider
import com.example.music.ui.theme.LocalDividerColor
import com.example.music.ui.theme.MUSICTheme
import com.example.music.ui.theme.SpaceExtraMedium
import com.example.music.ui.theme.SpaceOuter
import com.example.music.ui.theme.SpaceSmallHeight


@Composable
fun DiscoverRoute(
    //viewmodel 定义出来
    viewModel: DiscoveryViewModel=androidx.lifecycle.viewmodel.compose.viewModel()
) {
    //观察view model 的数据
    val datum by viewModel.topDatum.collectAsState()
    DiscoverScreen(
        topDatum = datum
    )

}

@Composable
fun DiscoverScreen(
    toggleDrawer: () -> Unit = {},
    toSearch: () -> Unit = {},
    topDatum: List<ViewData> = listOf(),
) {
    Scaffold(
        topBar = {
            DiscoveryTopBar(
                toggleDrawer, toSearch
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars) //排除底部导航栏影响
        // .exclude(WindowInsets.ime) 排除键盘影响
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = SpaceOuter),
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                topDatum.forEach{  viewData ->
                    if (viewData.sheets!= null){
                        items(viewData.sheets){   sheet ->
                            ItemSheet(data = sheet)
                        }
                    } else if (viewData.songs != null){
                        items(viewData.songs){   song ->
                            ItemSong(data = song)
                        }
                }



                }

            }
        }
    }
}


/**
 * 发现界面顶部标题栏
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DiscoveryTopBar(toggleDrawer: () -> Unit, toSearch: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = toggleDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,   //居中
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(LocalDividerColor.current)
                    .clickable {
                        toSearch()
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = stringResource(id = R.string.search_tip),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.wangyiyun),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(horizontal = SpaceExtraMedium)
                    .size(28.dp),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    )
}

@Composable
@Preview
fun DiscoveryScreenPreview(
    @PreviewParameter(DiscoveryPreviewParameterProvider::class)
    songs: List<Song>,
) {
    MUSICTheme {
        DiscoverScreen(
//            songs = songs,
        )
    }
}


