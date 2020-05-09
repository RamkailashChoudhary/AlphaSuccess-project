package com.app.alphasucess.service;
import com.app.alphasucess.ui.data.Result;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceLayer {

    static String API_BASE_URL = "http://demo1.stsm.co.in/api/App/";

    public static Object newInstance(Class className) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(className);
    }

}
