package com.mykeyhub.keyhub.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.mykeyhub.keyhub.Base.BaseKeyHubActivity;
import com.mykeyhub.keyhub.FloatView.ViewManager;
import com.mykeyhub.keyhub.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseKeyHubActivity {

    @OnClick(R.id.textView_activityMain_hello)
    public void show() {
        ViewManager.show1();
    }

    @BindView(R.id.textView_activityMain_hello)
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("abc", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("key", "1235543");
//        editor.commit();
        textView.setText(sp.getString("key", "aaa"));

    }
}
