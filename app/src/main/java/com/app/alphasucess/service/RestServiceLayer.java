package com.app.alphasucess.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestServiceLayer
{
    @POST("/login")
    @FormUrlEncoded
    Call<Object> loginService(@Field("UserName")String uName,@Field("Password")String password,@Field("grant_type")String grant_type);

   /* @POST("/AboutUs")
    aboutUsData();*/
}
