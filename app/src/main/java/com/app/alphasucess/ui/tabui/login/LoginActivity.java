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
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

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
}
