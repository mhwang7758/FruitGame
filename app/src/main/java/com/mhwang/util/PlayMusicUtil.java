package com.mhwang.util;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 项目名称：
 * 类描述：播放背景音乐
 * 作者：王明海
 * 创建时间：2016/11/22
 */
public class PlayMusicUtil {
    private static MediaPlayer mMediaPlayer;
    public static int playSound(Context context,int id){
        mMediaPlayer = MediaPlayer.create(context,id);
        int duration = mMediaPlayer.getDuration();
        mMediaPlayer.start();
        return duration;
    }
}
