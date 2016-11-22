package com.mhwang.fruitgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mhwang.bean.Block;
import com.mhwang.util.PlayMusicUtil;
import com.mhwang.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Block> mBlocks;
    private GameView gv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        gv_main = (GameView) findViewById(R.id.gv_main);
        gv_main.setBlocks(mBlocks);
        PlayMusicUtil.playSound(this,R.raw.again);
    }

    private void initData(){
        mBlocks = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            Block block = new Block();
            block.setNum(i);
            mBlocks.add(block);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
