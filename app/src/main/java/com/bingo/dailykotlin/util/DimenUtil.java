package com.bingo.dailykotlin.util;


import com.bingo.dailykotlin.App;

/**
 * Created by Eric on 2017/1/20.
 */

public class DimenUtil {
    public static float dp2px(float dp) {
        final float scale = App.appContext.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(float sp) {
        final float scale = App.appContext.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int getScreenSize() {
        return App.appContext.getResources().getDisplayMetrics().widthPixels;
    }
}
