package com.example.music.song.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.music.R
import com.example.music.design.component.MyAsyncImage
import com.example.music.model.Song
import com.example.music.ui.theme.DiscoveryPreviewParameterData.SONG
import com.example.music.ui.theme.LocalDividerColor
import com.example.music.ui.theme.MUSICTheme
import com.example.music.ui.theme.SpaceMedium
import com.example.music.ui.theme.SpaceSmallHeight
import com.example.music.ui.theme.extraSmallRoundedCornerShape
import com.example.music.util.ResourceUtil

//单曲item
@Composable
fun ItemSong(data: Song, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        MyAsyncImage(model = data.icon, modifier = Modifier.size(50.dp))



        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = SpaceMedium)
        ) {

            Text(
                text = data.title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            SpaceSmallHeight()
            Text(
                text = "${data.artist}-${data.album}",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

    }
}

@Preview
@Composable
fun ItemSongPreview() {

    MUSICTheme {
        ItemSong(data = SONG)
    }
}