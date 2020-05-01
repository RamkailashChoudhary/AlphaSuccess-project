package com.app.alphasucess.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        final Button backBtnView = findViewById(R.id.backBtnView);
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
