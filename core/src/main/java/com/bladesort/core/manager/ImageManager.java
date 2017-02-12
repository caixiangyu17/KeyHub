package com.bladesort.core.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bladesort.core.common.App;

/**
 * Created by leocx on 2016/9/6.
 */
public class ImageManager {
    public static Bitmap getBitmap(int id) {
        return BitmapFactory.decodeStream(App.getApp().getBaseContext().getResources().openRawResource(id));
    }

}

