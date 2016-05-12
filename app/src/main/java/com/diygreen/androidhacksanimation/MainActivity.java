package com.diygreen.androidhacksanimation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                overlay(TextSwitcherActivity.class);
                break;
            case R.id.btn_test2:
                overlay(ImageSwitcherActivity.class);
                break;
            case R.id.btn_test3:
                overlay(ViewFlipperActivity.class);
                break;
            case R.id.btn_test4:
                overlay(VGChildrenAnimActivity.class);
                break;
            case R.id.btn_test5:
                overlay(CanvasAnimActivity.class);
                break;
            case R.id.btn_test6:
                overlay(KenBurnsActivity.class);
                break;
        }
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
