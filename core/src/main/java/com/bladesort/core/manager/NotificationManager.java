package com.bladesort.core.manager;

import android.widget.Toast;

import com.bladesort.core.common.App;

/**
 * Created by leocx on 2016/9/1.
 */
public class NotificationManager {
    public static void toast(String msg) {
        Toast.makeText(App.getApp().getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
