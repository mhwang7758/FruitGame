package com.mhwang.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * 项目名称：图片工具类
 * 类描述：可以改变图片尺寸
 * 作者：王明海
 * 创建时间：2016/11/21
 */
public class BitmapUtil {
    public static Bitmap resizeBitmap(Bitmap bitmap,float width,float height){
        Matrix matrix = new Matrix();
        matrix.postScale(width/bitmap.getWidth(),height/bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),
                bitmap.getHeight(),matrix,true);
        bitmap.recycle();
        return newBitmap;
    }
}
