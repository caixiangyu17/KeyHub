package com.bladesort.core.manager;

import android.os.Build;

import com.bladesort.core.common.App;


/**
 * Created by leocx on 2016/9/8.
 */
public class ColorManager {
    public static int getColor(int resourceId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return App.getApp().getResources().getColor(resourceId, null);
        } else {
            return App.getApp().getResources().getColor(resourceId);
        }
    }

}
