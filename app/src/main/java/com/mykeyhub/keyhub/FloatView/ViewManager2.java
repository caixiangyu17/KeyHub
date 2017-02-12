package com.mykeyhub.keyhub.FloatView;

import android.view.View;

import com.mykeyhub.keyhub.R;
import com.bladesort.core.manager.FloatViewManager;

/**
 * Created by leocx on 2016/11/28.
 */

public class ViewManager2 {
    private static FloatViewManager test2Float = new FloatViewManager();


    public static void show2() {
        test2Float = new FloatViewManager();
        test2Float.setView(R.layout.activity_ttt);
        test2Float.setWindowType(FloatViewManager.WINDOW_TYPE_FLOATVIEW_LOCK);
        test2Float.show();
        test2Float.getView().findViewById(R.id.buttonClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2Float.dismiss();
            }
        });
    }

}
