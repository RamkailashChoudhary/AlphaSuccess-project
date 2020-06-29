package com.app.alphasucess.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebBaseActivity extends BaseActivity {

    private WebView webviewData;
    private String dataResponse;
    String responseData;
    private String urlPath = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_view);
        Bundle bundle = getIntent().getExtras();
        webviewData = findViewById(R.id.webViewData);
        webviewData.setWebViewClient(new MyWebClient());
        webviewData.getSettings().setJavaScriptEnabled(true);
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        TextView header=findViewById(R.id.middleTitle);
        if(bundle.getString("View-Name").toString().equalsIgnoreCase("Terms & Conditions")){
            urlPath = "/api/App/Terms";
        }else if(bundle.getString("View-Name").toString().equalsIgnoreCase("Privacy Policy")){
            urlPath = "/api/App/PrivacyPolicy";
        }else if(bundle.getString("View-Name").toString().equalsIgnoreCase("About Us")){
            urlPath = "/api/App/AboutUs";
        }else {
            urlPath = "/api/App/ContactUsRequest";
        }

        System.out.println("PRINT API PATH FINAL :"+urlPath);
        header.setText(bundle.getString("View-Name"));
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
        initLoadResourceData();
    }

    private class MyWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            Log.d("WEBVIEW","url :"+url );
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.d("WEBVIEW","ERROR :"+error.toString() );
        }
    }

    private void initLoadResourceData(){

        System.out.println("PRINT API PATH :"+urlPath);
        RestServiceLayer restServiceLayer = (RestServiceLayer)NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.resourceData(urlPath).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    Log.d("Data","Response Data "+response.body().get("data").toString());

                    JSONObject htmlContent = new JSONObject(response.body().get("data").toString());
                    String responseData1 = htmlContent.optString("htmlcontent");
                    webviewData.loadData(responseData1,"text/html", "utf-8");
                    webviewData.setBackgroundColor(Color.TRANSPARENT);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
