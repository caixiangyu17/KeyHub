package com.mykeyhub.keyhub.FloatView;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.mykeyhub.keyhub.R;
import com.bladesort.core.common.App;
import com.bladesort.core.manager.FloatViewManager;

import static android.content.Context.MODE_PRIVATE;
import static com.mykeyhub.keyhub.FloatView.ViewManager2.show2;

/**
 * Created by leocx on 2016/11/28.
 */

public class ViewManager {
    private static FloatViewManager test1Float = new FloatViewManager();

    public static void show1() {
        SharedPreferences sp = App.getApp().getBaseContext().getSharedPreferences("abc", MODE_PRIVATE);
        test1Float = new FloatViewManager();
        test1Float.setView(R.layout.activity_test);
        test1Float.setWindowType(FloatViewManager.WINDOW_TYPE_FLOATVIEW_LOCK);
        test1Float.show();
        test1Float.getView().findViewById(R.id.buttonClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
                new Thread() {
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        test1Float.dismiss();
                    }
                }.start();
            }
        });
        Button btn = (Button) test1Float.getView().findViewById(R.id.buttonClose);
        btn.setText(sp.getString("key", "sss"));
    }

}
