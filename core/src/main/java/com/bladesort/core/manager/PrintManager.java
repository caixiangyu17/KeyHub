package com.bladesort.core.manager;

import android.util.Log;

/**
 * Created by leocx on 2016/9/1.
 */
public class PrintManager {
    public static boolean isDebug = true;
    public final static String TAG = "QuicKSort";

    public static void print(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

}
