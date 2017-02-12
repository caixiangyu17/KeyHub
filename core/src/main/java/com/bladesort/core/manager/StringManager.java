package com.bladesort.core.manager;


import com.bladesort.core.common.App;

/**
 * Created by leocx on 2016/9/7.
 */
public class StringManager {
    public static String getString(int resourceId) {
        App app = (App) App.getApp();
        return app.getBaseContext().getResources().getString(resourceId);
    }


}
