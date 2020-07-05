package com.app.alphasucess.service;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.SplashActivity;
import com.app.alphasucess.ui.tabui.login.LoginResponse;
import com.app.alphasucess.utility.AlphaSharedPrefrence;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceLayer {

    static String API_BASE_URL = "http://demo1.stsm.co.in/";

    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;
    private static Context mContext;
    private static boolean continueOrNot = true;

    public static Object newInstance1(Class className) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(className);
    }

    public static <S> S newInstance(Class<S> serviceClass, String accessToken,Context context) {
        mContext = context;
        System.out.println("REQUEST TOKEN :"+accessToken);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder().addInterceptor(interceptor);
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        if(accessToken != null) {
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization","Bearer "+MyApplication.AUTH_TOKEN)
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            httpClient.authenticator((route, response) -> {
                if(responseCount(response) >= 2) {
                    // If both the original call and the call with refreshed token failed,
                    // it will probably keep failing, so don't try again.
                    System.out.println("REFRESH TOKEN COUNT SECTION");
                    return null;
                }

                RestServiceLayer tokenClient = (RestServiceLayer) NetworkServiceLayer.newInstance1(RestServiceLayer.class);
                Call<LoginResponse> call = tokenClient.refreshToken(accessToken,"refresh_token");
                try {
                    retrofit2.Response<LoginResponse> tokenResponse = call.execute();
                    if(tokenResponse.code() == 200) {
                        continueOrNot = true;
                        LoginResponse newToken = tokenResponse.body();
                        MyApplication.AUTH_TOKEN = newToken.getAccess_token();
                        MyApplication.REFRESH_TOKEN = newToken.getRefresh_token();
                        AlphaSharedPrefrence.setRefreshToken(newToken.getRefresh_token());
                        AlphaSharedPrefrence.setAccessTocken(newToken.getAccess_token());
                        System.out.println("REFRESH TOKEN TRUE SECTION");
                        return response.request().newBuilder()
                                .header("Authorization", "Bearer "+newToken.getAccess_token())
                                .build();
                    } else {

                        continueOrNot = false;
                        System.out.println("REFRESH TOKEN ELSE");
                        AlphaSharedPrefrence.clearData();
                        MyApplication.REFRESH_TOKEN = "";
                        MyApplication.AUTH_TOKEN = "";
                        Intent splashView = new Intent(mContext, SplashActivity.class);
                        mContext.startActivity(splashView);
                        ((AppCompatActivity)mContext).finish();
                        return null;
                    }
                } catch(IOException e) {
                    return null;
                }
            });
        }

        if(continueOrNot) {
            OkHttpClient client = httpClient.build();
            Retrofit retrofit = builder.client(client).build();
            return retrofit.create(serviceClass);
        }else {
            return null;
        }
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

}
