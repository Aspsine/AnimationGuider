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
 * Created Aspsine sf on 2014/11/18.
 */

public class GuiderFour extends RelativeLayout implements AnimationGuider{
    private ImageView ivTitleFour, ivArrowUpFour;
    private ImageView ivChart, ivData;
    public GuiderFour(Context context) {
        super(context);
        initView(context);
    }

    public GuiderFour(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GuiderFour(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.view_guider_four, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        view.getRootView().setLayoutParams(layoutParams);

        ivTitleFour = (ImageView)view.findViewById(R.id.ivTitleFour);
        ivArrowUpFour = (ImageView)view.findViewById(R.id.ivArrowUpFour);

        ivChart = (ImageView)view.findViewById(R.id.ivChart);
        ivData = (ImageView)view.findViewById(R.id.ivData);

        ivData.setVisibility(View.INVISIBLE);
        ivChart.setVisibility(View.INVISIBLE);

        addView(view);
    }

    @Override
    public void onGuiderDismiss(int position) {
        ivTitleFour.clearAnimation();
        ivArrowUpFour.clearAnimation();
        ivChart.clearAnimation();
        ivData.clearAnimation();

        ivData.setVisibility(View.INVISIBLE);
        ivChart.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGuiderShow(int position) {
        Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
        animationScaleIn.setAnimationListener(new ScaleInAnimationListener());
        ivTitleFour.startAnimation(animationScaleIn);

        Animation animTwinkle = AnimationUtils.loadAnimation(getContext(), R.anim.guider_twinkle);
        ivArrowUpFour.startAnimation(animTwinkle);
    }

    // 1
    protected  final class ScaleInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            ivChart.setVisibility(View.VISIBLE);
            Animation animDownIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_down_in);
            animDownIn.setAnimationListener(new DownInAnimationListener());
            ivChart.startAnimation(animDownIn);
        }
    }

    protected final class DownInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            ivData.setVisibility(View.VISIBLE);
            Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
            animationScaleIn.setAnimationListener(new GuiderAnimationListener(){
                @Override
                public void onAnimationEnd(Animation animation) {
                    ivData.setVisibility(View.INVISIBLE);
                }
            });
            ivData.startAnimation(animationScaleIn);
        }
    }


}