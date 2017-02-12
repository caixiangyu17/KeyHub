package com.bladesort.core.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.bladesort.core.common.App;

import butterknife.ButterKnife;

/**
 * Created by leocx on 2016/9/5.
 */
public class FloatViewManager {
    public static final int WINDOW_TYPE_DIALOG = 0x0001;
    public static final int WINDOW_TYPE_FLOATVIEW = 0x0002;
    public static final int WINDOW_TYPE_FLOATVIEW_LOCK = 0x0003;

    protected WindowManager windowManager;
    protected WindowManager.LayoutParams params;
    protected View view;

    public FloatViewManager() {
        init();
    }

    private void init() {
        windowManager = (WindowManager) App.getActivity().getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        setWindowType(WINDOW_TYPE_DIALOG);
        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
    }

    public void setWindowType(int id) {
        switch (id) {
            case WINDOW_TYPE_DIALOG:
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                break;
            case WINDOW_TYPE_FLOATVIEW:
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                break;
            case WINDOW_TYPE_FLOATVIEW_LOCK:
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                break;
            default:
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
    }

    public void setView(View view) {
        this.view = view;
        ButterKnife.bind(view);
    }

    public void setView(int viewId) {
        view = LayoutInflater.from(App.getActivity()).inflate(viewId, null);

    }
    public View getView(){
        return this.view;
    }

    public static void requestAlertWindowPermission() {
        int REQUEST_CODE = 1;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + App.getApp().getBaseContext().getPackageName()));
        App.getActivity().startActivityForResult(intent, REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isSystemAlertPermissionGranted() {
        final boolean result = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(App.getApp().getBaseContext());
        return result;
    }


    public void show() {
        if (isSystemAlertPermissionGranted()) {
            windowManager.addView(view, params);
        } else {
            requestAlertWindowPermission();
        }
    }

    public void dismiss() {
        windowManager.removeView(view);
    }
}
