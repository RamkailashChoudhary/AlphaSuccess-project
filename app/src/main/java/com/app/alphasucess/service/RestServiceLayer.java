package com.app.alphasucess.service;

import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.VerifyOTP;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.login.LoginResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RestServiceLayer
{
    @POST("/api/App/oauth/login")
    @FormUrlEncoded
    Call<LoginResponse> loginService(@Field("UserName")String uName, @Field("Password")String password, @Field("grant_type")String grant_type);

    @POST("/api/App/oauth/ForgotPassword")
    @FormUrlEncoded
    Call<Object> forgotPassword(@Field("Phone")String uName);

    @POST("/api/App/oauth/VerifyPhone")
    @FormUrlEncoded
    Call<VerifyOTP> verifyOtp(@Field("Phone")String uPhone, @Field("OTP")String uOTP);

    @POST("/api/App/oauth/UserRegister")
    @FormUrlEncoded
    Call<Object> signUpApi(@Field("Email")String uEmail,@Field("Name")String uName,@Field("Password")String uPass,@Field("Phone")String uPhone,@Field("StateID") String uStateId,@Field("Address") String uAddress,@Field("isReffered") boolean isRefer);

    @POST("api/App/StatesList")
    Call<Object> stateListData();

    @POST
    Call<JsonObject> resourceData(@Url String url);

    @POST("api/App/BooksList")
    @FormUrlEncoded
    Call<ResoureData<List<EbookData>>> ebookListData(@Header ("Authorization") String authorization, @Field("page_number")String pageNumber);
}
