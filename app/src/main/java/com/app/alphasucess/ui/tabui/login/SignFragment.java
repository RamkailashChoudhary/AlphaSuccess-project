package com.app.alphasucess.ui.tabui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.ForgotPasswordActivity;
import com.app.alphasucess.ui.HomeActivity;
import com.app.alphasucess.ui.tabui.signup.SignUpActivity;
import com.app.alphasucess.utility.AlphaSharedPrefrence;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.signinview, container, false);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);
        final TextView forgotPassword = view.findViewById(R.id.forgotPassword);
      //  final TextView signUpTxt = view.findViewById(R.id.signupBtn);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
              /*  loadingProgressBar.setVisibility(View.VISIBLE);
                loginApiService(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());*/
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {

            if (usernameEditText.getText().toString().trim().length()>0 && usernameEditText.getText().toString().trim().length()>0) {
             //   loadingProgressBar.setVisibility(View.VISIBLE);
                loginApiService(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }else
                Toast.makeText(getContext(),"Please Enter Credentials",Toast.LENGTH_LONG).show();

        });

        forgotPassword.setOnClickListener(v -> {
            Intent forgotPassword12 = new Intent(getContext(), ForgotPasswordActivity.class);
            startActivity(forgotPassword12);
        });

       /* signUpTxt.setOnClickListener(v -> {
            Intent forgotPassword13 = new Intent(getContext(), SignUpActivity.class);
            startActivity(forgotPassword13);
        });*/
        return view;
    }

    private void loginApiService(String username,String password){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,getContext());
        restServiceLayer.loginService(username,password,"password","Ghasguidshjadknkds78877jbjb2bujb4b4jb","Android").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                // Log.d("LoginActivity","Response Data "+response.body().getReplycode());
                if (response.body().getError()!=null && response.body().getError().equalsIgnoreCase("0") ){
                  //  loadingProgressBar.setVisibility(View.VISIBLE);
                    MyApplication.AUTH_TOKEN = response.body().getAccess_token();
                    MyApplication.USER_ID = response.body().getId();
                    MyApplication.USER_NAME = response.body().getName();
                    MyApplication.REFRESH_TOKEN = response.body().getRefresh_token();
                    AlphaSharedPrefrence.setUserId(response.body().getId());
                    AlphaSharedPrefrence.setAccessTocken(response.body().getAccess_token());
                    AlphaSharedPrefrence.setUserName(response.body().getName());
                    AlphaSharedPrefrence.setRefreshToken(response.body().getRefresh_token());
                    Intent forgotPassword1 = new Intent(getContext(), HomeActivity.class);
                    startActivity(forgotPassword1);
                    getActivity().finish();
                }
                else
                    Toast.makeText(getContext(),"Invalid "+response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                //loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
