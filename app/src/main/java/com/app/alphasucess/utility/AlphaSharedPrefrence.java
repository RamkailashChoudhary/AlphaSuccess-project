package com.app.alphasucess.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;


/**
 * Created by Ashish on 03/08/2017.
 */
public class AlphaSharedPrefrence {

    private static final String TAG= AlphaSharedPrefrence.class.getSimpleName();

    private static final String VERSION_CODE = "versioncode";
    private static SharedPreferences sharedPref;

    private static final String PREF_NAME = "alpha_preferences";
    private static final String PREF_USER = "userDetails";
    private static final String PREF_VERSION = "versioncode";

    public static final String LOGIN_DETAILS = "login";
    public static final String USER_ID = "userId";
    public static final String ACCESS_TOCKEN = "accessToken";
    public static final String USER_NAME = "userName";
    public static final String REFRESH_TOKEN = "refreshToken";


    public static void init(Context context) {

        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    }
    public static void clearData() {

        sharedPref.edit().clear().apply();

    }
    public static String getUserId(){
        return sharedPref.getString(USER_ID,null);
    }
    public static void setUserId(String userId){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_ID,userId);
        editor.apply();
    }

    public static String getRefreshToken(){
        return sharedPref.getString(REFRESH_TOKEN,null);
    }

    public static void setRefreshToken(String refreshToken){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(REFRESH_TOKEN,refreshToken);
        editor.apply();
    }

    public static String getAccessTocken(){
        return sharedPref.getString(ACCESS_TOCKEN,null);
    }

    public static void setAccessTocken(String accessTocken){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ACCESS_TOCKEN,accessTocken);
        editor.apply();
    }

    public static String getUserName(){
        return sharedPref.getString(USER_NAME,null);
    }

    public static void setUserName(String userName){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_NAME,userName);
        editor.apply();
    }






}
