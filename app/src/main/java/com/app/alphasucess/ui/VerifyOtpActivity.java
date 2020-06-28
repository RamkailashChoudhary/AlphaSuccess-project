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
import com.app.alphasucess.ui.data.model.VerifyOTP;
import com.app.alphasucess.ui.tabui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends BaseActivity {

    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_verification);
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        final EditText usernameEditText = findViewById(R.id.editText);
        TextView header=findViewById(R.id.middleTitle);
        Button btn=findViewById(R.id.forgotPasswordBtn);
        header.setText("Verify Otp");
        Bundle bundle = getIntent().getExtras();
        final String phoneNumber="";//bundle.getString("phoneNumber");
        btn.setOnClickListener(view ->{
            if (usernameEditText.getText().toString().trim().length()>0){
                forgotApiService(phoneNumber,usernameEditText.getText().toString());
            }else Toast.makeText(VerifyOtpActivity.this,"Enter OTP",Toast.LENGTH_LONG).show();
        });
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }


    private void forgotApiService(String phoneNumber,String otp){
        loadingProgressBar.setVisibility(View.VISIBLE);
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.verifyOtp(phoneNumber,otp).enqueue(new Callback<VerifyOTP>() {
            @Override
            public void onResponse(Call<VerifyOTP> call, Response<VerifyOTP> response) {
                loadingProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful()&&response.body().getReplycode().equals("1")){
                    Toast.makeText(VerifyOtpActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                    Intent forgotPassword1 = new Intent(VerifyOtpActivity.this, LoginActivity.class);
                    startActivity(forgotPassword1);
                    finish();
                }else {
                    Toast.makeText(VerifyOtpActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<VerifyOTP> call, Throwable t) {
                loadingProgressBar.setVisibility(View.GONE);

//                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(VerifyOtpActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
