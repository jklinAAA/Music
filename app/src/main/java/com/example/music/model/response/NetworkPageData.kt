package com.example.music.model.response

import kotlinx.serialization.Serializable

/**
 * 网络响应分页模型
 */
@Serializable
data class NetworkPageData<T>(   //调用要和对应的响应一致才能对的上
    /**
     * 列表
     */
    val list: List<T>? = null,

    /**
     * 分页数据
     */
    val pagination: NetworkPageMeta? = null,
)