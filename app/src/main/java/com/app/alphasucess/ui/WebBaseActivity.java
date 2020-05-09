package com.app.alphasucess.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;

import androidx.annotation.Nullable;

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

        webviewData.loadData("","text/html; charset=utf-8", null);
//        myWebView.loadDataWithBaseURL(null, htmlString, "text/html", "utf-8", null);
        final ImageView backBtnView = findViewById(R.id.backBtnView);

        TextView header=findViewById(R.id.middleTitle);
        header.setText(bundle.getString("View-Name"));
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initLoadResourceData(){


    }
}
