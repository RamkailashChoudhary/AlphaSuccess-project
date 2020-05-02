package com.app.alphasucess.ui.data;

import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.LoggedInUserData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUserData> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUserData fakeUser =
                    new LoggedInUserData(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
            restServiceLayer.loginService().enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {

                }
            });
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
