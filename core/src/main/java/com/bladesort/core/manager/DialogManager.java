package com.bladesort.core.manager;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bladesort.core.R;
import com.bladesort.core.common.App;

import butterknife.ButterKnife;

/**
 * Created by leocx on 2016/9/1.
 */
public abstract class DialogManager extends FloatViewManager {
    float defaultWidth = 0.8f;
    float defaultHeight = 0.5f;

    private TextView tv_dialogTitle;
    private LinearLayout ll_buttonGroup;
    private LinearLayout ll_dialog;
    private TextView tv_dialogText;
    private View view_dialog_bg;


    public DialogManager() {
        super();
        setView(R.layout.dialog_model);
        setWindowType(WINDOW_TYPE_DIALOG);
        ll_buttonGroup = ButterKnife.findById(view, R.id.ll_buttonGroup);
        ll_dialog = ButterKnife.findById(view, R.id.ll_dialog);
        tv_dialogText = ButterKnife.findById(view, R.id.tv_dialogText);
        view_dialog_bg = ButterKnife.findById(view, R.id.view_dialog_bg);


        setDialogSize((int) (ScreenManager.getInstance().getWidth() * defaultWidth),
                (int) (ScreenManager.getInstance().getHeight() * defaultHeight));
        animationIn();
    }

    public void animationIn() {
        Animation slideInBot = AnimationUtils.loadAnimation(App.getApp().getBaseContext(), R.anim.slide_in_bot);
        ll_dialog.setAnimation(slideInBot);
        Animation fadeIn = AnimationUtils.loadAnimation(App.getApp().getBaseContext(), R.anim.fade_in);
        view_dialog_bg.setAnimation(fadeIn);
        slideInBot.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view_dialog_bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void setText(String text) {
        tv_dialogText.setText(text);
    }

    public void setTitle(String title) {
        tv_dialogText.setText(title);
    }

    public void setButtonGroup(Button... btns) {
        ll_buttonGroup.removeAllViews();
        setButtonGroupMargin();
        setButtons(btns);
        setButtonGroupMargin();
    }


    private void setDialogSize(int width, int height) {

        ll_dialog.getLayoutParams().width = width;
        ll_dialog.getLayoutParams().height = height;
    }

    public void setButtons(Button... btns) {
        for (Button button : btns) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.width = 0;
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            params.weight = 1;
            button.setLayoutParams(params);
            ll_buttonGroup.addView(button);
        }
    }

    private void setButtonGroupMargin() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = 0;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.weight = 0.2f;
        View margin = new View(App.getActivity());
        margin.setLayoutParams(params);
        ll_buttonGroup.addView(margin);
    }


    @Override
    public void dismiss() {

        Animation fadeOut = AnimationUtils.loadAnimation(App.getApp().getBaseContext(), R.anim.fade_out);
        view_dialog_bg.clearAnimation();
        view_dialog_bg.setAnimation(fadeOut);
        Animation slideInBot = AnimationUtils.loadAnimation(App.getApp().getBaseContext(), R.anim.slide_out_bot);
        ll_dialog.clearAnimation();
        ll_dialog.setAnimation(slideInBot);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                DialogManager.super.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
