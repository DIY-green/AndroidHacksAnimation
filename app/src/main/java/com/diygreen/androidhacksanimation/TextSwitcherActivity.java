package com.diygreen.androidhacksanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Uses a TextSwitcher.
 */
public class TextSwitcherActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory,
        View.OnClickListener {

    private static final String[] TEXTS = { "First", "Second", "Third" };
    private int mTextsPosition = 0;

    private TextSwitcher mSwitcher;
    private TextSwitcher mTextSwitcher;

    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_textswitcher);

        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
        mTextSwitcher = (TextSwitcher) findViewById(R.id.text_switcher);

        mSwitcher.setFactory(this);
        mTextSwitcher.setFactory(this);

        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);

        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(this);

        updateCounter();
        onSwitchText();
    }

    public View makeView() {
        TextView t = new TextView(this);
        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        t.setTextSize(36);
        return t;
    }

    public void onClick(View v) {
        mCounter++;
        updateCounter();

        onSwitchText();
    }

    private void updateCounter() {
        mSwitcher.setText(String.valueOf(mCounter));
    }

    private void onSwitchText() {
        mTextSwitcher.setText(TEXTS[mTextsPosition]);
        setNextPosition();
    }

    private void setNextPosition() {
        mTextsPosition = (mTextsPosition + 1) % TEXTS.length;
    }

}
