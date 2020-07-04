package com.app.alphasucess.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.SubjectTopicData;
import com.app.alphasucess.ui.tabui.adapter.SubjectTopicAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectTopicActivity extends BaseActivity {

    private ProgressBar subscriptionDetailListProgress;
    private ImageView backBtnView;
    private TextView middleTitle;
    private RecyclerView recyclerViewSubscriptionList;
    private ArrayList<SubjectTopicData> subjectTopicDataList = new ArrayList<>();
    private SubjectTopicAdapter subjectTopicAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_list);

        backBtnView = findViewById(R.id.backBtnView);
        middleTitle = findViewById(R.id.middleTitle);
        recyclerViewSubscriptionList = findViewById(R.id.recyclerViewSubscriptionList);
        subscriptionDetailListProgress = findViewById(R.id.subscriptionDetailListProgress);
        initUI();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("SUBSCRIPTION-SUBJECT-ID") != null){
            System.out.println("Single");
            subscriptionSubjectTopicList(bundle.getString("SUBSCRIPTION-SUBJECT-ID"));
        }
        backBtnView.setOnClickListener(view -> onBackPressed());
    }

    private void initUI(){

        subjectTopicAdapter = new SubjectTopicAdapter(this,subjectTopicDataList);
        recyclerViewSubscriptionList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSubscriptionList.setAdapter(subjectTopicAdapter);
    }

    private void subscriptionSubjectTopicList(String subject_Id){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.subscriptionSubjectTopicList("Bearer "+ MyApplication.AUTH_TOKEN,subject_Id).enqueue(new Callback<ResoureData<List<SubjectTopicData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<SubjectTopicData>>> call, Response<ResoureData<List<SubjectTopicData>>> response) {
                middleTitle.setText(response.body().getMessage());
                subscriptionDetailListProgress.setVisibility(View.INVISIBLE);
                subjectTopicDataList.addAll(response.body().getData());
                subjectTopicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResoureData<List<SubjectTopicData>>> call, Throwable t) {
                subscriptionDetailListProgress.setVisibility(View.INVISIBLE);
            }
        });
    }
}
