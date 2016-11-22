package com.mhwang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.mhwang.bean.Block;
import com.mhwang.fruitgame.R;
import com.mhwang.util.BitmapUtil;
import com.mhwang.util.ScreenUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    private int mRecIds[] = {R.drawable.apple,R.drawable.ic_orange_small,R.drawable.ic_orange_big,
            R.drawable.ic_banana_small,R.drawable.ic_banana_big,R.drawable.ic_car_small,
            R.drawable.ic_car_big,R.drawable.ic_cucumber_small,R.drawable.ic_cucumber_big,
            R.drawable.ic_grapes_small,R.drawable.ic_grapes_big,R.drawable.ic_pear_small,
            R.drawable.ic_pear_big,R.drawable.ic_seeds_small,R.drawable.ic_seeds_big,
            R.drawable.ic_watermellon_small,R.drawable.ic_watermellon_big,R.drawable.ic_start_small,
            R.drawable.ic_start_big,R.drawable.apple};
    private void showLog(String msg){
        Log.i("--GameView--->", msg);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = ScreenUtils.getScreenSize(context);
        mBlockSize = (dm.widthPixels - MARGIN*2)/6;
        mPaint = new Paint();
        showLog("Screen width is " + dm.widthPixels + " the blockSize is " + mBlockSize);
        mFirstBlockLeft = MARGIN;
        mCenterBlockLeft = MARGIN;
        mFirstBlockTop = MARGIN;
        mCenterBlockTop = mBlockSize+MARGIN;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mSelectBlock > 20){
            mSelectBlock = 0;
        }
        for (int i = 0; i < mBlocks.size(); i++){
            // 如果方块的数字刚好是被选中的数字，则变色
            if (i == mSelectBlock){
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(5);
                mPaint.setColor(Color.GREEN);
            }else{
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(Color.GRAY);
                mPaint.setStrokeWidth(1);
            }
            float left,top;
            left = mBlocks.get(i).getLeft();
            top = mBlocks.get(i).getTop();
            Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), mRecIds[i]);
            canvas.drawBitmap(BitmapUtil.resizeBitmap(bitmap, mBlockSize, mBlockSize),
                    left, top, mPaint);
            bitmap.recycle();
            canvas.drawRect(left,top,left+mBlockSize,top+mBlockSize,mPaint);
        }
        if (!stop) {
            postInvalidateDelayed(1000);
            mSelectBlock++;
        }
    }

    private boolean stop = false;
    public void startGame(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
              stop = true;
            }
        },5000);
    }

    /** 设置块
     * @param blocks
     */
    public void setBlocks(List<Block> blocks){
        mBlocks = blocks;
        Block block;
        int centerCount = 0;
        // 初始化块状坐标
        for (int i = 0; i < mBlocks.size(); i++){
            block = mBlocks.get(i);
            if (i < TYPE){
                block.setLeft(mFirstBlockLeft);
                block.setTop(mFirstBlockTop);
                mFirstBlockLeft += mBlockSize;
            }else if (i>=TYPE*2 && i<= TYPE *3 -1){
                mFirstBlockLeft -= mBlockSize;
                block.setLeft(mFirstBlockLeft);
                block.setTop(mFirstBlockTop+mBlockSize*6);
            }else{
                if (centerCount % 2 == 0) {
                    block.setLeft(mCenterBlockLeft);
                    block.setTop(mCenterBlockTop);
                }else{
                    block.setLeft(mCenterBlockLeft +mBlockSize*4);
                    block.setTop(mCenterBlockTop);
                    mCenterBlockTop += mBlockSize;
                }
                centerCount++;
            }
        }
    }
}
