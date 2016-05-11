package com.diygreen.androidhacksanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class CanvasAnimActivity extends AppCompatActivity {

    private DrawView mDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        mDrawView = new DrawView(this);
        mDrawView.height = display.getHeight();
        mDrawView.width = display.getWidth();

        setContentView(mDrawView);
    }
}
