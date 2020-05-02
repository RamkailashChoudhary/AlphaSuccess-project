package com.app.alphasucess.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestServiceLayer
{
    @POST("/login")
    Call<Object> loginService();


}
