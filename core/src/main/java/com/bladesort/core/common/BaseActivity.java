package com.bladesort.core.common;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.bladesort.core.manager.NotificationManager;

import java.util.HashMap;

/**
 * Created by leocx on 2016/8/31.
 */
public class BaseActivity extends Activity {
    private final static int REQUEST_PERMISSION = 0x1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init() {
        App.setActivity(this);
    }


    protected void showDialog() {
        YesNoDialog yesNoDialog = new YesNoDialog() {
            @Override
            public void onClickYes() {
                NotificationManager.toast("yes");
            }

            @Override
            public void onClickNo() {
                this.dismiss();
            }
        };
        yesNoDialog.show();
    }

    /**
     * When calling this method, you should override the function:
     * "onRequestPermissionsResult(HashMap<String, Boolean> results)" as a callback
     * Pay attention, this is not the system function in Activity.
     * This is a new function with different parameters in BaseActivity.
     *
     * @param permissionIds
     */
    protected void requestPermissions(String... permissionIds) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionIds, REQUEST_PERMISSION);
        }
    }

    protected void onRequestPermissionsResult(HashMap<String, Boolean> results) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                HashMap<String, Boolean> results = new HashMap<>();
                for (int i = 0; i < permissions.length; i++) {
                    results.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
                }
                onRequestPermissionsResult(results);
                break;
        }
    }
}
