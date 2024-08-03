package com.example.music.util

import java.util.Calendar

object SuperDateUtil {   //静态工具类
    fun currentYear():Int{     //获取当前年份
        return  Calendar.getInstance().get(Calendar.YEAR)
    }

    fun currentDayOfMonth():Int{     //获取当前月份    同理改以下下面这个就可以得到其他的
        return  Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }
}