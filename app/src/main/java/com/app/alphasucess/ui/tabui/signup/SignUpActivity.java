package com.app.alphasucess.ui.tabui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.alphasucess.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignUpFragment.newInstance())
                    .commitNow();
        }
    }
}