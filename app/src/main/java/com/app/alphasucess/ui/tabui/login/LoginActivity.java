package com.app.alphasucess.ui.tabui.login;

import android.app.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.ForgotPasswordActivity;
import com.app.alphasucess.ui.HomeActivity;
import com.app.alphasucess.ui.tabui.signup.SignUpActivity;
import com.app.alphasucess.utility.AlphaSharedPrefrence;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hide();
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final TextInputEditText usernameEditText = findViewById(R.id.username);
        final TextInputEditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        final TextView forgotPassword = findViewById(R.id.forgotPassword);
        final TextView signUpTxt = findViewById(R.id.signupBtn);

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);

            //Complete and destroy login activity once successful
            finish();
        });

        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginApiService(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {

            if (usernameEditText.getText().toString().trim().length()>0 && usernameEditText.getText().toString().trim().length()>0) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginApiService(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }else
              Toast.makeText(LoginActivity.this,"Please Enter Credentials",Toast.LENGTH_LONG).show();

        });

        forgotPassword.setOnClickListener(view -> {
            Intent forgotPassword12 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(forgotPassword12);
        });

        signUpTxt.setOnClickListener(view -> {
            Intent forgotPassword13 = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(forgotPassword13);
        });
    }

    private void hide(){

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void loginApiService(String username,String password){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.loginService(username,password,"password").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

               // Log.d("LoginActivity","Response Data "+response.body().getReplycode());
                if (response.body().getReplycode()!=null && response.body().getReplycode().equalsIgnoreCase("1") ){
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    MyApplication.AUTH_TOKEN = response.body().getAccess_token();
                    MyApplication.USER_ID = response.body().getId();
                    MyApplication.USER_NAME = response.body().getName();
                    AlphaSharedPrefrence.setUserId(response.body().getId());
                    AlphaSharedPrefrence.setAccessTocken(response.body().getAccess_token());
                    AlphaSharedPrefrence.setUserName(response.body().getName());
                    Intent forgotPassword1 = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(forgotPassword1);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this,"Invalid "+response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
