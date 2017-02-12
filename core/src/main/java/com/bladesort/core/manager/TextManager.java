package com.bladesort.core.manager;

import android.util.TypedValue;

import com.bladesort.core.common.App;


/**
 * Created by leocx on 2016/10/31.
 */

public class TextManager {
    public static float applyDp2px(int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dpVal, App.getApp().getResources().getDisplayMetrics());
    }

    public static float getSizeByPx(int resourceId) {
        float value = App.getApp().getResources().getDimension(resourceId);
        float density = App.getApp().getResources().getDisplayMetrics().density;
        return applyDp2px((int) (value / density));
    }

    public static float getSizeByDp(int resourceId) {
        float value = App.getApp().getResources().getDimension(resourceId);
        float density = App.getApp().getResources().getDisplayMetrics().density;
        return (int) (value / density);
    }

}
