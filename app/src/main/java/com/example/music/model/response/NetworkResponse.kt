package com.example.music.model.response

//解析网络响应
data class NetworkResponse<T> (
    //真实数据 类型是泛型
    val data:T?=null,
    //状态码 表示成功
    val status:Int =0,
    //出错的提示 出错不一定有
    val message :String?= null,
)




