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
public class GuiderThree extends RelativeLayout implements AnimationGuider {
    private ImageView ivTitleThree, ivArrowUpThree;
    private ImageView ivHammer, ivFlashing, ivSign;

    public GuiderThree(Context context) {
        super(context);
        initView(context);
    }

    public GuiderThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GuiderThree(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_guider_three, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        view.getRootView().setLayoutParams(layoutParams);

        ivTitleThree = (ImageView) view.findViewById(R.id.ivTitleThree);
        ivArrowUpThree = (ImageView) view.findViewById(R.id.ivArrowUpThree);

        ivHammer = (ImageView) view.findViewById(R.id.ivHammer);
        ivFlashing = (ImageView) view.findViewById(R.id.ivFlashing);
        ivSign = (ImageView) view.findViewById(R.id.ivSign);

        ivSign.setVisibility(View.INVISIBLE);
        ivFlashing.setVisibility(View.INVISIBLE);
        addView(view);
    }

    @Override
    public void onGuiderDismiss(int position) {
        ivTitleThree.clearAnimation();
        ivArrowUpThree.clearAnimation();
        ivSign.clearAnimation();
        ivHammer.clearAnimation();
        ivFlashing.clearAnimation();

        ivFlashing.setVisibility(View.INVISIBLE);
        ivSign.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGuiderShow(int position) {
        // 0
        Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in);
        animationScaleIn.setAnimationListener(new ScaleInAnimationListener());
        ivTitleThree.startAnimation(animationScaleIn);

        Animation animTwinkle = AnimationUtils.loadAnimation(getContext(), R.anim.guider_twinkle);
        ivArrowUpThree.startAnimation(animTwinkle);
    }

    // 1
    protected  final class ScaleInAnimationListener extends GuiderAnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            ivSign.setVisibility(View.VISIBLE);
            Animation animDownIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_sign_up_in);
            animDownIn.setAnimationListener(new DownInAnimationListener());
            ivSign.startAnimation(animDownIn);
        }
    }

    //2
    protected final class DownInAnimationListener extends GuiderAnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            Animation animRotate = AnimationUtils.loadAnimation(getContext(), R.anim.guider_rotate);
            animRotate.setAnimationListener(new RotateAnimationListener());
            ivHammer.startAnimation(animRotate);
        }
    }


    //3
    protected final class RotateAnimationListener extends GuiderAnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            final Animation animationScaleIn = AnimationUtils.loadAnimation(getContext(), R.anim.guider_scale_in_flashing);
            ivFlashing.setVisibility(View.VISIBLE);
            ivFlashing.startAnimation(animationScaleIn);
        }
    }

}