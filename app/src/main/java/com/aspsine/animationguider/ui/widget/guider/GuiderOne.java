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
 * Created by sf on 2014/11/18.
 */
public class GuiderOne extends RelativeLayout implements AnimationGuider {
    private ImageView ivTitleOne, ivArrowUpOne;

    private ImageView[] ivMapSpots= new ImageView[6];
    private int[] ivMapSpotResIds = {R.id.ivMapSpotOne, R.id.ivMapSpotTwo, R.id.ivMapSpotThree, R.id.ivMapSpotFour, R.id.ivMapSpotFive, R.id.ivMapSpotSix};

    public GuiderOne(Context context) {
        super(context);
        initView(context);
    }

    public GuiderOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GuiderOne(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_guider_one, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        view.getRootView().setLayoutParams(layoutParams);

        ivTitleOne = (ImageView) view.findViewById(R.id.ivTitleOne);
        ivArrowUpOne = (ImageView) view.findViewById(R.id.ivArrowUpOne);

        // init map spot ImageViews and make them invisible
        for(int i=0;i<ivMapSpots.length;i++){
            ivMapSpots[i]=(ImageView) view.findViewById(ivMapSpotResIds[i]);
            ivMapSpots[i].setVisibility(View.INVISIBLE);
        }

        addView(view);
    }

    @Override
    public void onGuiderDismiss(int position) {
        ivTitleOne.clearAnimation();
        ivArrowUpOne.clearAnimation();

        // clear map spot ImageViews' animation and make them invisible again
        for(ImageView iv : ivMapSpots){
            iv.clearAnimation();
            iv.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onGuiderShow(int position) {
        Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
        animationScaleIn.setAnimationListener(new ScaleInAnimationListener());
        ivTitleOne.startAnimation(animationScaleIn);

        Animation animTwinkle = AnimationUtils.loadAnimation(getContext(), R.anim.guider_twinkle);
        ivArrowUpOne.startAnimation(animTwinkle);
    }

    protected final class ScaleInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            Animation animTopIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_top_in);
            for(ImageView iv : ivMapSpots){
                iv.setVisibility(View.VISIBLE);
                iv.startAnimation(animTopIn);
            }
        }
    }




}
