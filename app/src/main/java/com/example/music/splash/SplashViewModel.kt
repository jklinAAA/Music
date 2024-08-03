package com.example.music.splash

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel : ViewModel() {  //启动界面vm
    private var timer: CountDownTimer? = null

    //倒计时秒数
    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft: StateFlow<Long> = _timeLeft

    //是否跳转到主界面
    val navigateToMain = MutableStateFlow(false)

    init {
        delayToNext(1)  //给参数 倒计时的秒数
    }

    private fun delayToNext(time: Long = 3000) {
        timer = object : CountDownTimer(time, 1000) {
            //每次倒计时执行
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished / 1000 + 1

            }

            //倒计时结束
            override fun onFinish() {
                toNext()
            }

        }.start()
    }

    private fun toNext() {
        navigateToMain.value = true
    }

    fun onSkipAdClick() {  //跳过广告
//        if (timer!=null){   相当于下面一行
//            timer.cancel()
//        }
        timer ?.cancel()
        toNext()
    }
}