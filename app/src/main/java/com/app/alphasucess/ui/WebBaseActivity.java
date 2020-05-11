package com.app.alphasucess.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_view);

        Bundle bundle = getIntent().getExtras();

        webviewData = findViewById(R.id.webViewData);
        webviewData.getSettings().setJavaScriptEnabled(true);
        String responseData = "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" " +
                "content=\"text/html; charset=utf-8\"> <html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=windows-1250\">"+
                "<meta name=\"spanish press\" content=\"spain, spanish newspaper, news,economy,politics,sports\"><title></title></head><body id=\"body\">"+
                "<script src=\"http://www.myscript.com/a\"></script>şlkasşldkasşdksaşdkaşskdşk</body></html>";
        webviewData.loadData(responseData,"text/html", "utf-8");
//        myWebView.loadDataWithBaseURL(null, htmlString, "text/html", "utf-8", null);
        final ImageView backBtnView = findViewById(R.id.backBtnView);


  //      initLoadResourceData();
        TextView header=findViewById(R.id.middleTitle);
        header.setText(bundle.getString("View-Name"));
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initLoadResourceData(){

        RestServiceLayer restServiceLayer = (RestServiceLayer)NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.resourceData("/api/App/AboutUs").enqueue(new Callback<JsonObject>() {
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
