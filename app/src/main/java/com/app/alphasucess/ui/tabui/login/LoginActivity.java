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
import com.app.alphasucess.ui.tabui.signup.SignUpFragment;
import com.app.alphasucess.utility.AlphaSharedPrefrence;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    ProgressBar loadingProgressBar;
    private Button loginTabView,signupTabView;
    private TextView signTabViewSelected,signUpTabViewSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hide();
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        signTabViewSelected = findViewById(R.id.signTabViewSelected);
        signUpTabViewSelected = findViewById(R.id.signupTabViewSelected);
        loginTabView = findViewById(R.id.signTabView);
        signupTabView = findViewById(R.id.signupTabView);
        loadingProgressBar = findViewById(R.id.loading);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new SignFragment())
                .commitNow();
        loginTabView.setOnClickListener(view -> {
            signTabViewSelected.setVisibility(View.VISIBLE);
            signUpTabViewSelected.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new SignFragment())
                    .commitNow();
        });
        signupTabView.setOnClickListener(view -> {
            signTabViewSelected.setVisibility(View.INVISIBLE);
            signUpTabViewSelected.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, SignUpFragment.newInstance())
                    .commitNow();
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

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN);
        restServiceLayer.loginService(username,password,"password","Ghasguidshjadknkds78877jbjb2bujb4b4jb","Android").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loadingProgressBar.setVisibility(View.GONE);
               // Log.d("LoginActivity","Response Data "+response.body().getReplycode());
                if (response.body().getError()!=null && response.body().getError().equalsIgnoreCase("0") ){
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
