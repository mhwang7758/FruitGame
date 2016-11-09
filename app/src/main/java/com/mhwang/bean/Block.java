package com.mhwang.bean;

/**
 * 项目名称：
 * 类描述：机器上的方块
 * 作者：王明海
 * 创建时间：2016/11/9
 */
public class Block {
    /**
     *  方块的序号
     */
    private int mNum;

    /**
     *  方块的内容
     */
    private String mText;


    public int getNum() {
        return mNum;
    }

    public void setNum(int num) {
        mNum = num;
    }


    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
