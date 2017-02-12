package com.bladesort.core.manager;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.bladesort.core.common.App;

import java.lang.reflect.Field;


public class ScreenManager {

    private static ScreenManager screenManager = null;
    int titleBarHeight = -1;
    int statusBarHeight = -1;
    int width = -1;
    int height = -1;


    public static ScreenManager getInstance() {
        if (screenManager == null) {
            screenManager = new ScreenManager();
        }
        return screenManager;
    }

    public ScreenManager() {
        init();
    }

    private void init() {
        refreshSize();
        refreshStatusBarHeight();
        refreshTitleBarHeight();
    }

    public void refreshSize() {
        WindowManager windowManager = (WindowManager) App.getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }

    private void refreshStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = App.getActivity().getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    private void refreshTitleBarHeight() {
        class Calculater implements Runnable {
            public void run() {
                Rect rect = new Rect();
                App.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int statusBarHeight = rect.top;
                int contentTop = App.getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
                titleBarHeight = contentTop - statusBarHeight;
            }
        }
        Runnable a = new Calculater();
        a.run();
    }

    public int getTitleBarHeight() {
        if (titleBarHeight == -1) {
            refreshTitleBarHeight();
        }
        return titleBarHeight;
    }

    public int getStatusBarHeight() {
        if (statusBarHeight == -1) {
            refreshStatusBarHeight();
        }
        return statusBarHeight;
    }

    public int getWidth() {
        if (width == -1) {
            refreshSize();
        }
        return width;
    }

    public int getHeight() {
        if (height == -1) {
            refreshSize();
        }
        return height;
    }
}

