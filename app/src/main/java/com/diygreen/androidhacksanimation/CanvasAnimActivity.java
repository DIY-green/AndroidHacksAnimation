package com.diygreen.androidhacksanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class CanvasAnimActivity extends AppCompatActivity {

    private DrawView mDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取屏幕尺寸
        Display display = getWindowManager().getDefaultDisplay();
        mDrawView = new DrawView(this);
        // 这里简单简单起见为状态栏和ActionBar的高度取了个固定值 200
        mDrawView.height = display.getHeight() - 200;
        mDrawView.width = display.getWidth();

        setContentView(mDrawView);
    }
}
