package com.bladesort.core.manager;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.bladesort.core.common.App;


/**
 * Created by leocx on 2016/11/3.
 */

public class KeyboardManager {
    private static boolean isKeyboardShown = false;

    public static void showInputKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) App.getApp().getBaseContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public static void hideInputKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) App.getApp().getBaseContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = App.getApp().getActivity().getCurrentFocus();
        if (view == null) {
            view = new View(App.getApp().getActivity());
        }
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static boolean isKeyboardShown(final View rootView) {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                rootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                    isKeyboardShown = true;
                } else {
                    isKeyboardShown = false;
                }
            }
        });
//        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
//                if (heightDiff > SizeManager.dpToPx(200)) { // if more than 200 dp, it's probably a keyboard...
//                    isKeyboardShown = true;
//                } else {
//                    isKeyboardShown = false;
//                }
//            }
//        });
        return isKeyboardShown;
    }
}
