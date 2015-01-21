package com.aspsine.animationguider.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.aspsine.animationguider.ui.widget.guider.AnimationGuider;
import com.aspsine.animationguider.ui.widget.viewpager.PagerAdapter;

import java.util.List;

/**
 * Created by zhangf on 2014/11/17.
 * ViewPager adapter
 */
public class GuidePagerAdapter extends PagerAdapter {
    private List<AnimationGuider> views;

    public GuidePagerAdapter(List<AnimationGuider> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object o) {
        return v == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View)views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

