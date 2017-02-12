package com.bladesort.core.common;

import android.view.View;
import android.widget.Button;

import com.bladesort.core.R;
import com.bladesort.core.manager.DialogManager;
import com.bladesort.core.manager.StringManager;

/**
 * Created by leocx on 2016/9/7.
 */
public abstract class YesNoDialog extends DialogManager {
    public YesNoDialog() {
        super();
        setDefaultButtonGroup();
    }

    public void setDefaultButtonGroup() {
        Button btnYes = new Button(App.getActivity());
        btnYes.setText(StringManager.getString(R.string.btn_yes));
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickYes();
            }
        });
        Button btnNo = new Button(App.getActivity());
        btnNo.setText(StringManager.getString(R.string.btn_no));
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNo();
            }
        });
        setButtonGroup(btnNo, btnYes);
    }

    abstract public void onClickYes();

    abstract public void onClickNo();

}
