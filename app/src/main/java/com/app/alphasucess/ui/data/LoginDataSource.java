package com.app.alphasucess.ui.data;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.LoggedInUserData;
import com.app.alphasucess.ui.tabui.login.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUserData> login(String username, String password) {

            LoggedInUserData fakeUser =
                    new LoggedInUserData(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN);
            restServiceLayer.loginService(username,password,"grant_type","sjfshf76ff8gu8dfdfbf","Android").enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                   // return new Result.Success<>(call);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                  //  return new Result.Error(new IOException("Error logging in", t.getMessage()));
                }
            });
            return null;
    }

    public void logout() {

    }
}
