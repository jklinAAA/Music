package com.example.music.model

import com.example.music.ui.theme.Constant


data class Song(
    /**
     * 歌曲的唯一标识符
     */
    val id: String,

    /**
     * 歌曲标题
     */
    val title: String,

    /**
     * 音频文件
     */
    val uri: String,

    /**
     * 网络封面
     */
    val icon: String = "",

    /**
     * 专辑的名称
     */
    val album: String,

    /**
     * 艺术家的名称
     */
    val artist: String,

    /**
     * 歌曲的流派
     */
    val genre: String,

    val lyricStyle: Int = Constant.VALUE0,
    val lyric: String = "",

    /**
     * 歌曲的轨道号
     *
     * 从1开始
     */
    val trackNumber: Int = 1,

    /**
     * 专辑中的歌曲总数
     */
    val totalTrackCount: Int = 1,

//    /**
//     * 歌曲的持续时间
//     *
//     * 毫秒
//     */
//    val duration: Int=0,
) {
//    fun toSongEntity(): SongEntity {
//        return SongEntity(
//            id = id,
//            title = title,
//            uri = ResourceUtil.r2(uri),
//            icon = icon,
//            album = album,
//            artist = artist,
//            genre = genre,
//            lyricStyle = lyricStyle,
//            lyric = lyric,
//            trackNumber = trackNumber,
//            totalTrackCount = totalTrackCount,
//            playList = true,
//        )
//    }
}
