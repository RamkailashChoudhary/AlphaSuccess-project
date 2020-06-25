package com.app.alphasucess.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.SubscriptionData;
import com.app.alphasucess.ui.tabui.adapter.SubscriptionAdapter;
import com.app.alphasucess.ui.tabui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends BaseActivity {

    ProgressBar loadingProgressBar;
    private SubscriptionAdapter subscriptionAdapter;
    private RecyclerView recyclerViewSubscription;
    private ArrayList<SubscriptionData> subscriptionDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        recyclerViewSubscription = findViewById(R.id.recyclerViewSubscription);
        TextView header=findViewById(R.id.middleTitle);
        header.setText("SUBSCRIPTION PLAN");

        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });

        subscriptionAdapter = new SubscriptionAdapter(this,subscriptionDataList);
        recyclerViewSubscription.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSubscription.setAdapter(subscriptionAdapter);

        subscriptionDataList();
    }

    private void subscriptionDataList(){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN);
        restServiceLayer.subscriptionList("Bearer "+ MyApplication.AUTH_TOKEN).enqueue(new Callback<ResoureData<List<SubscriptionData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<SubscriptionData>>> call, Response<ResoureData<List<SubscriptionData>>> response) {
                subscriptionDataList.addAll(response.body().getData());
                subscriptionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResoureData<List<SubscriptionData>>> call, Throwable t) {

            }
        });
    }
}
