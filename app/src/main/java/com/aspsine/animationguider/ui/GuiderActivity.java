package com.aspsine.animationguider.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


import com.aspsine.animationguider.R;
import com.aspsine.animationguider.adapter.GuidePagerAdapter;
import com.aspsine.animationguider.ui.MainActivity;
import com.aspsine.animationguider.ui.widget.guider.AnimationGuider;
import com.aspsine.animationguider.ui.widget.guider.GuiderFive;
import com.aspsine.animationguider.ui.widget.guider.GuiderFour;
import com.aspsine.animationguider.ui.widget.guider.GuiderOne;
import com.aspsine.animationguider.ui.widget.guider.GuiderThree;
import com.aspsine.animationguider.ui.widget.guider.GuiderTwo;
import com.aspsine.animationguider.ui.widget.viewpager.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspsine on 2014/11/17.
 * Guider page
 */
public class GuiderActivity extends Activity implements View.OnClickListener {

    private ViewPager viewPager;

    private GuidePagerAdapter guidePagerAdapter;

    private List<AnimationGuider> guiders = new ArrayList<AnimationGuider>();

    private int prePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider);
        initLoginView();
        initGuiderView();
        startAnimation(0);
    }

    /**
     * init the visibility of buttons
     */
    private void initLoginView() {
        Button btnLoginIn = (Button) findViewById(R.id.btnLoginIn);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        Button btnExpNow = (Button) findViewById(R.id.btnExpNow);
        btnLoginIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnExpNow.setOnClickListener(this);
    }

    private void initGuiderView() {
        guiders.add(new GuiderOne(this));
        guiders.add(new GuiderTwo(this));
        guiders.add(new GuiderThree(this));
        guiders.add(new GuiderFour(this));
        guiders.add(new GuiderFive(this));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        guidePagerAdapter = new GuidePagerAdapter(guiders);
        viewPager.setAdapter(guidePagerAdapter);
        viewPager.setOnPageChangeListener(new OnGuidePageChangeListener());
    }

    /**
     * start animation on page change
     *
     * @param position
     */
    private void startAnimation(int position) {
        if (prePosition != position) {
            guiders.get(prePosition).onGuiderDismiss(prePosition);
        }
        guiders.get(position).onGuiderShow(position);
    }

    /**
     * viewpager OnPageChangeListener
     */
    public class OnGuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageSelected(int position) {
            startAnimation(position);
            prePosition = position;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginIn:
                loginIn();
                break;
            case R.id.btnSignUp:
                signUp();
                break;
            case R.id.btnExpNow:
                intentToMain();
                break;
        }
    }

    /**
     * intent to MainTabActivity
     */
    private void intentToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * sign up
     */
    private void signUp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * login in
     */
    private void loginIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
