package com.app.alphasucess.ui.tabui.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.alphasucess.R;

public class OnlineTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ontestScreenView, new TestScreenFragment())
                    .commitNow();
        }
    }
}
