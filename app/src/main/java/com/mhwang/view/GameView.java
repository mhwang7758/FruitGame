package com.mhwang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.mhwang.bean.Block;
import com.mhwang.util.ScreenUtils;

import java.util.List;

/**
 * 项目名称：
 * 类描述：
 * 作者：王明海
 * 创建时间：2016/11/9
 */
public class GameView extends View {

    /**
     *  第一块的左上角坐标
     */
    private float mFirstBlockLeft;
    private float mFirstBlockTop;
    private float mCenterBlockLeft;
    private float mCenterBlockTop;
    /**
     * 方块宽高
     */
    private float mBlockSize;

    /**
     * 方块距离屏幕边距
     */
    private final int MARGIN = 10;
    /**
     * 类型为5*5
     */
    private final int TYPE = 5;
    /**
     *  方块集合
     */
    private List<Block> mBlocks;

    private Paint mPaint;

    /**
     *  选中的方块，方块被选中会变色
     */
    private int mSelectBlock = 0;

    private void showLog(String msg){
        Log.i("--GameView--->", msg);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = ScreenUtils.getScreenSize(context);
        mBlockSize = (dm.widthPixels - MARGIN*2)/5;
        mPaint = new Paint();
        showLog("Screen width is " + dm.widthPixels + " the blockSize is " + mBlockSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mFirstBlockLeft = MARGIN;
        mCenterBlockLeft = MARGIN;
        mFirstBlockTop = MARGIN;
        mCenterBlockTop = mBlockSize+MARGIN;
        if (mSelectBlock > 20){
            mSelectBlock = 0;
        }
        int centerCount = 0;
        for (int i = 0; i < mBlocks.size(); i++){
            // 如果方块的数字刚好是被选中的数字，则变色
            if (i == mSelectBlock){
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(Color.GREEN);
            }else{
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(Color.GRAY);
            }

            if (i < TYPE){
                canvas.drawRect(mFirstBlockLeft,mFirstBlockTop,
                        mFirstBlockLeft+mBlockSize,mFirstBlockTop+mBlockSize,mPaint);
                mFirstBlockLeft += mBlockSize;
            }else if (i>=TYPE*2 && i<= TYPE *3 -1){
                mFirstBlockLeft -= mBlockSize;
                canvas.drawRect(mFirstBlockLeft,mFirstBlockTop+mBlockSize*6,
                        mFirstBlockLeft+mBlockSize,mFirstBlockTop+mBlockSize*7,mPaint);
            }else{
                if (centerCount % 2 == 0) {
                    canvas.drawRect(mCenterBlockLeft,mCenterBlockTop,
                            mCenterBlockLeft+mBlockSize,mCenterBlockTop+mBlockSize,mPaint);
                }else{
                    canvas.drawRect(mCenterBlockLeft+mBlockSize*4,mCenterBlockTop,
                            mCenterBlockLeft+mBlockSize * 5,mCenterBlockTop+mBlockSize,mPaint);
                    mCenterBlockTop += mBlockSize;
                }
                centerCount++;
            }

        }
    }

    public void setBlocks(List<Block> blocks){
        mBlocks = blocks;
    }
}
