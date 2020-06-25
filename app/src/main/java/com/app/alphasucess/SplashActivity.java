package com.app.alphasucess;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.app.alphasucess.ui.HomeActivity;
import com.app.alphasucess.ui.VerifyOtpActivity;
import com.app.alphasucess.ui.VideoPlayerActivity;
import com.app.alphasucess.ui.tabui.login.LoginActivity;
import com.app.alphasucess.utility.AlphaSharedPrefrence;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        hide();
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginPage;
                if (AlphaSharedPrefrence.getUserId()!=null){
                    MyApplication.AUTH_TOKEN = AlphaSharedPrefrence.getAccessTocken();
                    MyApplication.USER_ID = AlphaSharedPrefrence.getUserId();
                    MyApplication.USER_NAME = AlphaSharedPrefrence.getUserName();
                    MyApplication.REFRESH_TOKEN = AlphaSharedPrefrence.getRefreshToken();
                    loginPage = new Intent(SplashActivity.this, HomeActivity.class);
                }else
                 loginPage = new Intent(SplashActivity.this, LoginActivity.class);

                startActivity(loginPage);
                finish();
            }
        },2000);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    private void playerVire(){

        Intent intent = new Intent(this, VideoPlayerActivity.class);
        Sample sample = getSampleObj("Video",Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),null,false,null,null,null,null);
        sample.addToIntent(intent);
        startActivity(intent);
    }

    private Sample getSampleObj(String sampleName, Uri uri, String extension, boolean isLive, Sample.DrmInfo drmInfo,String adTagUri,String sphericalStereoMode,
                              Sample.SubtitleInfo subtitleInfo){

        return new Sample.UriSample(
                sampleName,
                uri,
                extension,
                isLive,
                drmInfo,
                adTagUri != null ? Uri.parse(adTagUri) : null,
                sphericalStereoMode,
                subtitleInfo);
    }
}
