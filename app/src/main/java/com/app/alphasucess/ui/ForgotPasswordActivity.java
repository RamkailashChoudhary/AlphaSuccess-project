package com.app.alphasucess.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {

    ProgressBar loadingProgressBar;
    Button forgotPasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        final EditText usernameEditText = findViewById(R.id.editText);
        TextView header = findViewById(R.id.middleTitle);
        forgotPasswordBtn = findViewById(R.id.forgotPasswordBtn);
        header.setText("Forgot Password");
        usernameEditText.setOnClickListener(view -> {
            if (usernameEditText.getText().toString().trim().length() > 0) {
//                forgotApiService(usernameEditText.getText().toString());
            } else
                Toast.makeText(ForgotPasswordActivity.this, "Enter Register Number", Toast.LENGTH_LONG).show();
        });

        forgotPasswordBtn.setOnClickListener(view -> {
            if (usernameEditText.getText().toString().trim().length() > 0) {
//                forgotApiService(usernameEditText.getText().toString());
            } else
                Toast.makeText(ForgotPasswordActivity.this, "Enter Register Number", Toast.LENGTH_LONG).show();
        });
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }


}