package com.bladesort.core.manager;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.bladesort.core.common.App;


/**
 * Created by leocx on 2016/11/30.
 */

public class SizeManager {
    public static int getPixel(int resourceId) {
        return App.getApp().getBaseContext().getResources().getDimensionPixelSize(resourceId);
    }

    public static float dpToPx(float valueInDp) {
        DisplayMetrics metrics = App.getApp().getBaseContext().getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }
}
