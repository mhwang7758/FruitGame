package com.mhwang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mhwang.fruitgame.R;
import com.mhwang.util.BitmapUtil;

/**
 * 项目名称：
 * 类描述：底部筹码押注View
 * 作者：王明海
 * 创建时间：2016/12/11
 */

public class ChipView extends View {
    private int width;
    private int height;
    public ChipView(Context context) {
        super(context);
    }

    private void showLog(String msg){
        Log.i("--ChipView-->",msg);
    }

    private Paint mPaint;

    private final int BLOCK_NUMS = 9;
    private Bitmap mBitmaps[];
    private boolean bitmapsExits = false;

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        showLog("coustract width is "+width+" height is "+height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        showLog(" onDraw width is "+width+" height is "+height);
        float blockWidth = width / BLOCK_NUMS;
        float blockHeight = height / 4;
        initBitmap(blockWidth,blockHeight);
        // 画分数矩形
        scoreRectangle(canvas,blockWidth,blockHeight);
        // 画分数下面的图形
        BitmapRectangle(canvas,blockWidth,blockHeight);
    }

    /**
     * @param canvas
     */
    private void scoreRectangle(Canvas canvas,float blockWidth,float blockHeight){
        float left = 0;
        float top = 0;
        for(int i = 0; i < BLOCK_NUMS; i++) {
            canvas.drawRect(left, top, left + blockWidth, top + blockHeight, mPaint);
            left = left + blockWidth;
        }
    }

    private void BitmapRectangle(Canvas canvas,float blockWidth,float blockHeight){
        float left = 0;
        float top = blockHeight;
        for(int i = 0; i < mBitmaps.length; i++) {
            canvas.drawBitmap(mBitmaps[i],left,top,mPaint);
            canvas.drawRect(left,top,left+blockWidth,top+blockHeight*3,mPaint);
            left += blockWidth;
        }
    }

    private void initBitmap(float width,float height){
        if (bitmapsExits) {
            showLog("bimaps is not empty!");
            return;
        }
        // 加载图片资源
        int mRecIds[] = {R.drawable.apple,R.drawable.ic_orange_big,
                R.drawable.ic_banana_big,
                R.drawable.ic_car_big,R.drawable.ic_cucumber_big,
                R.drawable.ic_grapes_big,
                R.drawable.ic_pear_big,R.drawable.ic_seeds_big,
                R.drawable.ic_watermellon_big};
        mBitmaps = new Bitmap[mRecIds.length];
        Bitmap bitmap;
        for(int i = 0; i < mRecIds.length; i++){
            bitmap = BitmapFactory.decodeResource(getResources(), mRecIds[i]);
            mBitmaps[i] = BitmapUtil.resizeBitmap(bitmap,width,height*3);
        }
        bitmapsExits = true;
    }
}
