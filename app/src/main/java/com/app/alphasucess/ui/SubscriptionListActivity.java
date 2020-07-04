package com.app.alphasucess.ui;

import android.os.Bundle;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.SubscriptionListData;
import com.app.alphasucess.ui.tabui.adapter.SubscriptionListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.alphasucess.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionListActivity extends BaseActivity {

    private RecyclerView recyclerViewSubscriptionList;
    private SubscriptionListAdapter subscriptionListAdapter;
    private ArrayList<SubscriptionListData> subscriptionListData = new ArrayList<>();
    private ProgressBar subscriptionDetailListProgress;
    private ImageView backBtnView;
    private TextView middleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_list);

        backBtnView = findViewById(R.id.backBtnView);
        middleTitle = findViewById(R.id.middleTitle);
        recyclerViewSubscriptionList = findViewById(R.id.recyclerViewSubscriptionList);
        subscriptionDetailListProgress = findViewById(R.id.subscriptionDetailListProgress);
        initUI();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("SUBSCRIPTION-ID") != null){
            System.out.println("Single");
            subscriptionDetailListAPI(bundle.getString("SUBSCRIPTION-ID"));
        }

        backBtnView.setOnClickListener(view -> onBackPressed());
    }

    private void initUI(){

        subscriptionListAdapter = new SubscriptionListAdapter(this,subscriptionListData);
        recyclerViewSubscriptionList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSubscriptionList.setAdapter(subscriptionListAdapter);
    }

   private void subscriptionDetailListAPI(String id){
       RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,this);
       restServiceLayer.subscriptionDetailList("Bearer "+ MyApplication.AUTH_TOKEN,id).enqueue(new Callback<ResoureData<List<SubscriptionListData>>>() {
           @Override
           public void onResponse(Call<ResoureData<List<SubscriptionListData>>> call, Response<ResoureData<List<SubscriptionListData>>> response) {
               middleTitle.setText(response.body().getMessage());
               subscriptionDetailListProgress.setVisibility(View.INVISIBLE);
               subscriptionListData.addAll(response.body().getData());
               subscriptionListAdapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<ResoureData<List<SubscriptionListData>>> call, Throwable t) {
               subscriptionDetailListProgress.setVisibility(View.INVISIBLE);
           }
       });
   }
}