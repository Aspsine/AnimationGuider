package com.aspsine.animationguider.ui.widget.guider;

/**
 * Created by Aspsine on 2014/11/18.
 */
public interface AnimationGuider {

    /**
     * call when the guider page show up;
     * @param position
     */
    public abstract void onGuiderShow(int position);


    /**
     * call when the guider page dismiss;
     * @param position
     */
    public abstract void onGuiderDismiss(int position);
}
