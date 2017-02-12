package com.bladesort.core.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by leocx on 2016/11/25.
 */

public class ActivityManager {

    public static void startActivityWithParas(Activity activity, Class clazz, Object[]... paras) {
        Intent intent = new Intent(activity, clazz);
        Bundle bundle = new Bundle();
        for (Object[] para : paras) {
            String key = (String) para[0];
            Object val = para[1];
            if (val instanceof String) {
                bundle.putString(key, (String) val);
            } else if (val instanceof Integer) {
                bundle.putInt(key, (int) val);
            } else if (val instanceof Float) {
                bundle.putFloat(key, (float) val);
            }
        }
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static int getIntPara(Activity activity, String key, int defaultVal) {
        Bundle bundle = activity.getIntent().getExtras();
        if (bundle.containsKey(key)) {
            return bundle.getInt(key);
        } else {
            return defaultVal;
        }
    }

    public static float getFloatPara(Activity activity, String key, float defaultVal) {
        Bundle bundle = activity.getIntent().getExtras();
        if (bundle.containsKey(key)) {
            return bundle.getFloat(key);
        } else {
            return defaultVal;
        }
    }

    public static String getStringPara(Activity activity, String key, String defaultVal) {
        Bundle bundle = activity.getIntent().getExtras();
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return defaultVal;
        }
    }
}
