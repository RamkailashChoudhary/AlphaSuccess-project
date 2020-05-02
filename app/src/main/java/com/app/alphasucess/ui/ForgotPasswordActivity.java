package com.app.alphasucess.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        final ImageView backBtnView = findViewById(R.id.backBtnView);

        TextView header=findViewById(R.id.middleTitle);
        header.setText("Forgot Password");
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
