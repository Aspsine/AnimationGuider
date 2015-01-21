package com.aspsine.animationguider.ui.widget.guider;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aspsine.animationguider.R;


/**
 * Created by Aspsine on 2014/11/18.
 */

public class GuiderFive extends RelativeLayout implements AnimationGuider{
    private ImageView ivTitleFive, ivArrowUpFive;
    private ImageView ivPhone, ivColorful;
    private AnimationDrawable animDrawable;
    public GuiderFive(Context context) {
        super(context);
        initView(context);
    }

    public GuiderFive(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GuiderFive(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.view_guider_five, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        view.getRootView().setLayoutParams(layoutParams);

        ivTitleFive = (ImageView)view.findViewById(R.id.ivTitleFive);
        ivArrowUpFive = (ImageView)view.findViewById(R.id.ivArrowUpFive);

        ivPhone = (ImageView)view.findViewById(R.id.ivPhone);
        ivColorful = (ImageView)view.findViewById(R.id.ivColorful);

        ivPhone.setBackgroundResource(R.drawable.guider_frame);
        animDrawable = (AnimationDrawable) ivPhone.getBackground();

        ivArrowUpFive.setVisibility(View.INVISIBLE);
        ivColorful.setVisibility(View.INVISIBLE);
        addView(view);
    }

    @Override
    public void onGuiderDismiss(int position) {
        ivTitleFive.clearAnimation();
//        ivArrowUpFive.clearAnimation();

        animDrawable.stop();

        ivColorful.clearAnimation();
        ivColorful.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGuiderShow(int position) {
        Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
        animationScaleIn.setAnimationListener(new ScaleInAnimationListener());
        ivTitleFive.startAnimation(animationScaleIn);
        animDrawable.start();
//        Animation animTwinkle = AnimationUtils.loadAnimation(getContext(), R.anim.guider_twinkle);
//        ivArrowUpFive.startAnimation(animTwinkle);
    }

    // 1
    protected  final class ScaleInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            ivColorful.setVisibility(View.VISIBLE);
            Animation animScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
            ivColorful.startAnimation(animScaleIn);
        }
    }

}