package com.mykeyhub.keyhub.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mykeyhub.keyhub.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.buttonLoginActivityCancel, R.id.buttonLoginActivitySubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLoginActivityCancel:
                finish();
                break;
            case R.id.buttonLoginActivitySubmit:

                break;
        }
    }
}
