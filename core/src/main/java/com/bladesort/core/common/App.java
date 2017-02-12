package com.bladesort.core.common;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.bladesort.core.manager.PrintManager;

/**
 * Created by leocx on 2016/9/1.
 */
public class App extends Application {
    private static Application app;
    private static Activity activity;
    private static Service service;

    @Override
    public void onCreate() {
        super.onCreate();
        PrintManager.print("--------------init app");
        PrintManager.print(getBaseContext().toString());
        app = this;
    }

    public static App getApp() {
        return (App) app;
    }

    public static Activity getActivity() {
        return activity;
    }

    public static Service getService() {
        return service;
    }

    public static void setActivity(Activity activity) {
        App.activity = activity;
    }

    public static void setService(Service service) {
        App.service = service;
    }
}
