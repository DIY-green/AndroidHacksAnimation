package com.diygreen.androidhacksanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.animation.AnimatorProxy;

import java.util.Random;

public class KenBurnsActivity extends AppCompatActivity implements Animator.AnimatorListener {

    private static final int ANIM_COUNT = 4;
    private static final int[] PHOTOS = new int[] { R.mipmap.lock_wallpaper01,
            R.mipmap.lock_wallpaper02, R.mipmap.lock_wallpaper03, R.mipmap.lock_wallpaper04,
            R.mipmap.lock_wallpaper05, R.mipmap.lock_wallpaper06 };

    private FrameLayout mContainer;
    private ImageView mView;
    private Random mRandom = new Random();
    private int mIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContainer = new FrameLayout(this);
        mContainer.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        mView = createNewView();
        mContainer.addView(mView);

        setContentView(mContainer);
    }

    private ImageView createNewView() {
        ImageView ret = new ImageView(this);
        ret.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        ret.setScaleType(ImageView.ScaleType.FIT_XY);
        ret.setImageResource(PHOTOS[mIndex]);
        mIndex = (mIndex + 1 < PHOTOS.length) ? mIndex + 1 : 0;
        return ret;
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextAnimation();
    }

    private void nextAnimation() {
        AnimatorSet anim = new AnimatorSet();
        // 生成随机数，实现随机播放动画
        final int index = mRandom.nextInt(ANIM_COUNT);
        switch (index) {
            case 0:
                // 缩放动画
                anim.playTogether(
                        ObjectAnimator.ofFloat(mView, "scaleX", 1.5f, 1f),
                        ObjectAnimator.ofFloat(mView, "scaleY", 1.5f, 1f));
                break;
            case 1:
                // 缩放动画
                anim.playTogether(
                        ObjectAnimator.ofFloat(mView, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(mView, "scaleY", 1, 1.5f));
                break;
            case 2:
                AnimatorProxy.wrap(mView).setScaleX(1.5f);
                AnimatorProxy.wrap(mView).setScaleY(1.5f);
                // 位移动画
                anim.playTogether(ObjectAnimator.ofFloat(mView,
                        "translationY", 80f, 0f));
                break;
            case 3:
            default:
                AnimatorProxy.wrap(mView).setScaleX(1.5f);
                AnimatorProxy.wrap(mView).setScaleY(1.5f);
                // 位移动画
                anim.playTogether(ObjectAnimator.ofFloat(mView,
                        "translationX", 0f, 40f));
                break;
        }
        // 设置动画持续时间
        anim.setDuration(3000);
        // 设置动画监听器
        anim.addListener(this);
        // 播放动画
        anim.start();
    }

    @Override
    public void onAnimationCancel(Animator arg0) {
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        mContainer.removeView(mView);
        mView = createNewView();
        mContainer.addView(mView);
        nextAnimation();
    }

    @Override
    public void onAnimationRepeat(Animator arg0) {
    }

    @Override
    public void onAnimationStart(Animator arg0) {
    }
}
