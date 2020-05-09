package com.app.alphasucess.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestServiceLayer
{
    @POST("/api/App/oauth/login")
    @FormUrlEncoded
    Call<Object> loginService(@Field("UserName")String uName,@Field("Password")String password,@Field("grant_type")String grant_type);

    @POST("/api/App/oauth/ForgotPassword")
    @FormUrlEncoded
    Call<Object> forgotPassword(@Field("Phone")String uName);

    @POST("/api/App/oauth/UserRegister")
    @FormUrlEncoded
    Call<Object> signUpApi(@Field("Email")String uEmail,@Field("Name")String uName,@Field("Password")String uPass,@Field("Phone")String uPhone,@Field("StateID") String uStateId,@Field("Address") String uAddress,@Field("isReffered") boolean isRefer);

   /* @POST("/AboutUs")
    aboutUsData();*/
}
