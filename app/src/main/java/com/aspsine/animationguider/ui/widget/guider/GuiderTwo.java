package com.aspsine.animationguider.ui.widget.guider;

import android.content.Context;
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
public class GuiderTwo extends RelativeLayout implements AnimationGuider {
    private ImageView ivTitleTwo, ivArrowUpTwo;
    private ImageView ivWords, ivPlane;
    private RelativeLayout rlGlass;

    public GuiderTwo(Context context) {
        super(context);
        initView(context);
    }

    public GuiderTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GuiderTwo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_guider_two, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        view.getRootView().setLayoutParams(layoutParams);

        ivTitleTwo = (ImageView) view.findViewById(R.id.ivTitleTwo);
        ivArrowUpTwo = (ImageView) view.findViewById(R.id.ivArrowUpTwo);

        ivWords = (ImageView) view.findViewById(R.id.ivWords);
        ivPlane = (ImageView) view.findViewById(R.id.ivPlane);

        rlGlass = (RelativeLayout)view.findViewById(R.id.rlGlass);

        ivWords.setVisibility(View.INVISIBLE);
        addView(view);
    }

    @Override
    public void onGuiderDismiss(int position) {
        ivTitleTwo.clearAnimation();
        ivArrowUpTwo.clearAnimation();
        rlGlass.clearAnimation();
        ivWords.clearAnimation();
        ivPlane.clearAnimation();

        ivPlane.clearAnimation();
        ivWords.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGuiderShow(int position) {
        Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
        animationScaleIn.setAnimationListener(new ScaleInAnimationListener());
        ivTitleTwo.startAnimation(animationScaleIn);

        Animation animTwinkle = AnimationUtils.loadAnimation(getContext(), R.anim.guider_twinkle);
        ivArrowUpTwo.startAnimation(animTwinkle);
    }

    // 1
    protected  final class ScaleInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.guider_shake);
            animShake.setAnimationListener(new ShakeAnimationListener());
            rlGlass.startAnimation(animShake);
        }
    }

    protected final class ShakeAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            ivWords.setVisibility(View.VISIBLE);
            Animation animFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_fade_in);
            animFadeIn.setAnimationListener(new FadeInAnimationListener());
            ivWords.startAnimation(animFadeIn);
        }
    }

    protected final class FadeInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            Animation animFly = AnimationUtils.loadAnimation(getContext(), R.anim.guider_fly);
            ivPlane.startAnimation(animFly);
        }
    }
}
