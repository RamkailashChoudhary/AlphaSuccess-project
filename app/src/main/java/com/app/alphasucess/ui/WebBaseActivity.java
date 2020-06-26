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
        responseData = "<html><head></head><body><h1>Privacy Policy<p></p></h1><p ><p>&nbsp;</p></p><p >Last updated: <span style=\"background:#FFF2CC\">(add date)<p></p></span></p><p ><p>&nbsp;</p></p><p ><span style=\"background:#FFF2CC\">My Company (change this)</span>  (\"us\", \"we\", or \"our\") operates <span style=\"background:#FFF2CC\">http://www.mysite.com (change this)</span> (the  \"Site\"). This page informs you of our policies regarding the  collection, use and disclosure of Personal Information we receive from users of  the Site.<p></p></p><p ><p>&nbsp;</p></p><p >We use your Personal Information only for providing and  improving the Site. By using the Site, you agree to the collection and use of  information in accordance with this policy.<p></p></p><p ><p>&nbsp;</p></p><p ><b>Information  Collection And Use<p></p></b></p><p ><p>&nbsp;</p></p><p >While using our Site, we may ask you to provide us with  certain personally identifiable information that can be used to contact or  identify you. Personally identifiable information may include, but is not  limited to your name (\"Personal Information\").<p></p></p><p ><p>&nbsp;</p></p><p ><b>Log Data<p></p></b></p><p ><p>&nbsp;</p></p><p >Like many site operators, we collect information that your  browser sends whenever you visit our Site (\"Log Data\").<p></p></p><p ><p>&nbsp;</p></p><p >This Log Data may include information such as your  computer's Internet Protocol (\"IP\") address, browser type, browser  version, the pages of our Site that you visit, the time and date of your visit,  the time spent on those pages and other statistics.<p></p></p><p ><p>&nbsp;</p></p><p>                                                                        <span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,sans-serif;  mso-fareast-font-family:Arial;mso-ansi-language:EN-US;mso-fareast-language:  EN-US;mso-bidi-language:AR-SA\">In addition, we may use third party services  such as Google Analytics that collect, monitor and analyze this</span><br></p></body></html>";
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
                    String responseData = "<html><body style='color:red'>"+htmlContent.optString("htmlcontent")+"</body></html>";
                    webviewData.loadData(responseData,"text/html", "utf-8");
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
