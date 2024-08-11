package com.example.music.splash

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.music.R
import com.example.music.ui.theme.MUSICTheme
import com.example.music.util.SuperDateUtil

@Composable
fun SplashRoute(
    toMain: () -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    val timeLeft by viewModel.timeLeft.collectAsStateWithLifecycle()
    val navigateToMain by viewModel.navigateToMain.collectAsState()

    SplashScreen(
        year = SuperDateUtil.currentYear(),
        timeLeft =timeLeft,
        onSkipAdClick = viewModel::onSkipAdClick,
    )
    if(navigateToMain){
        LaunchedEffect(key1 = true) {
            toMain()

        }
    }

}

@Composable
fun SplashScreen(
    year: Int = 2024,
    timeLeft:Long =0,
     onSkipAdClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Image( //网易云logo
            painter = painterResource(id = R.drawable.wangyiyun),
            contentDescription = "启动logo",    //给残障人士
            modifier = Modifier
                .padding(top = 150.dp)
                .align(Alignment.TopCenter)
                .size(width = 100.dp, height = 100.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 70.dp)
                .align(Alignment.BottomCenter)
                .clickable { onSkipAdClick() }
        )
//版权文本
        Text(
//            text = stringResource(id = R.string.copyright, year),
            text = stringResource(id = R.string.v1),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        )
        Text(text ="倒计时:$timeLeft",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 20.dp, end = 20.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun SplashRoutePreview(): Unit {
    MUSICTheme {
        SplashScreen(
            year = 2024
        )
    }
}


