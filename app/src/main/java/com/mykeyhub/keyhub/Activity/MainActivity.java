package com.mykeyhub.keyhub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mykeyhub.keyhub.Base.BaseKeyHubActivity;
import com.mykeyhub.keyhub.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseKeyHubActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
    }

    @OnClick({R.id.buttonMainActivityDropIn, R.id.buttonMainActivityPickUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonMainActivityDropIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonMainActivityPickUp:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
