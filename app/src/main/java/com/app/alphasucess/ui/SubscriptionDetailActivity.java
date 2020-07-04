package com.app.alphasucess.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.SubscriptionData;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView txtheading,txtprice,txtdesc;
    private ImageView subscriptionCardImg,backBtnView;
    private Button details,ordernow;
    private ProgressBar subscriptionDetailProgress;
    private String SUB_ID = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookldetail);
        txtheading = findViewById(R.id.txtheading);
        backBtnView = findViewById(R.id.backBtnView);
        txtdesc = findViewById(R.id.txtdesc);
        subscriptionCardImg = findViewById(R.id.subscriptionCardImg);
        details = findViewById(R.id.details);
        ordernow = findViewById(R.id.ordernow);
        txtprice = findViewById(R.id.txtprice);
        details.setOnClickListener(this);
        ordernow.setOnClickListener(this);
        subscriptionDetailProgress = findViewById(R.id.subscriptionDetailProgress);
        backBtnView.setOnClickListener(view -> onBackPressed());
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("SUBSCRIPTION-PLAN-ID") != null){

            singleSubscriptionData(bundle.getString("SUBSCRIPTION-PLAN-ID"));
        }
    }

    private void intUIData(SubscriptionData subscriptionData){
        SUB_ID = subscriptionData.getId();
        txtheading.setText(subscriptionData.getBrief());
        Picasso.with(this).load("http://demo1.stsm.co.in/" + subscriptionData.getHomebannerurl())
                .into(subscriptionCardImg);
        txtdesc.setText(subscriptionData.getDescription());
        txtprice.setText(subscriptionData.getPrice());
    }

    private void singleSubscriptionData(String id){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.singleSubscriptionData("Bearer "+ MyApplication.AUTH_TOKEN,id).enqueue(new Callback<ResoureData<SubscriptionData>>() {
            @Override
            public void onResponse(Call<ResoureData<SubscriptionData>> call, Response<ResoureData<SubscriptionData>> response) {

                subscriptionDetailProgress.setVisibility(View.INVISIBLE);
                intUIData(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResoureData<SubscriptionData>> call, Throwable t) {
                subscriptionDetailProgress.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == ordernow){

        }else if(view == details){

            Intent subscriptionDetail = new Intent(this,SubscriptionListActivity.class);
            subscriptionDetail.putExtra("SUBSCRIPTION-ID",SUB_ID);
            startActivity(subscriptionDetail);
        }
    }
}
