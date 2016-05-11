package com.diygreen.androidhacksanimation;

import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class VGChildrenAnimActivity extends AppCompatActivity {

    private LinearLayout mTestLL;
    private ListView mContentLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vgchildrenanim);

        initView();
        initData();
    }

    private void initView() {
        mTestLL = (LinearLayout) findViewById(R.id.ll_test);
        mContentLV = (ListView) findViewById(R.id.lv_content);
    }

    private void initData() {
        mTestLL.setLayoutAnimation(new LayoutAnimationController(
                AnimationUtils.loadAnimation(this, R.anim.push_up_in),
                0.5f));
        AnimationSet set = new AnimationSet(false); // 传入 false 表示组成动画集中的动画使用不同的 Interpolator
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        alphaAnimation.setInterpolator(new FastOutSlowInInterpolator());
        set.addAnimation(alphaAnimation);

        Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f,
                Animation.RELATIVE_TO_SELF,
                -1.0f,
                Animation.RELATIVE_TO_SELF,
                0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setInterpolator(new LinearOutSlowInInterpolator());
        set.addAnimation(translateAnimation);

        LayoutAnimationController lac = new LayoutAnimationController(
                set,
                1f);
        // order one of {@link #ORDER_NORMAL}, {@link #ORDER_REVERSE} or {@link #ORDER_RANDOM}
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);

        mContentLV.setLayoutAnimation(lac);
//        mContentLV.setLayoutAnimation(new LayoutAnimationController(
//                AnimationUtils.loadAnimation(this, R.anim.list_animation),
//                0.5f));
        mContentLV.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Countries.COUNTRIES));
        for (int i = 0; i < 10; i++) {
            addChild();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mTestLL.setLayoutAnimation(new LayoutAnimationController(
                        AnimationUtils.loadAnimation(this, R.anim.push_up_in),
                        0.5f));
                addChild();
                break;
            case R.id.btn_remove:
                mTestLL.setLayoutAnimation(new LayoutAnimationController(
                        AnimationUtils.loadAnimation(this, R.anim.push_right_out),
                        1f));
                removeChild();
                break;
        }
    }

    private void addChild() {
        TextView tv = new TextView(this);
        tv.setText("DIY");
        mTestLL.addView(tv);
    }

    private void removeChild() {
        int count = mTestLL.getChildCount();
        if (count <= 0) return;
        mTestLL.removeViewAt(0);
    }
}
