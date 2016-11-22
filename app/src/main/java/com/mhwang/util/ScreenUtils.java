package com.mhwang.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.WeakHashMap;

/**
 * 项目名称：
 * 类描述：获取屏幕宽高
 * 作者：王明海
 * 创建时间：2016/11/9
 */
public class ScreenUtils {
    public static DisplayMetrics getScreenSize(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);
        return dm;
    }
}
