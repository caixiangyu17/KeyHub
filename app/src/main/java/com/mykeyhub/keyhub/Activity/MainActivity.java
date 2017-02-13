package com.mykeyhub.keyhub.Activity;

import android.os.Bundle;

import com.mykeyhub.keyhub.Base.BaseKeyHubActivity;
import com.mykeyhub.keyhub.R;


public class MainActivity extends BaseKeyHubActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
    }
}
