package com.app.alphasucess.ui.tabui.test;

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
import com.app.alphasucess.ui.tabui.test.adapters.LeaderBoardAdapter;
import com.app.alphasucess.ui.tabui.test.adapters.LeaderboardData;
import com.app.alphasucess.ui.tabui.test.adapters.TestResultData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardActivity extends BaseActivity {

    private RecyclerView recyclerViewleaderBoard;
    private TextView titleView;
    private String testId,testResult,testMarks;
    private ProgressBar subscriptionDetailListProgress;
    private LeaderBoardAdapter leaderBoardAdapter;
    private ArrayList<LeaderboardData> leaderboardDataList = new ArrayList<>();
    private ImageView backBtnView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_activity);
        recyclerViewleaderBoard = findViewById(R.id.recyclerViewleaderBoard);
        titleView = findViewById(R.id.middleTitle);
        backBtnView = findViewById(R.id.backBtnView);
        subscriptionDetailListProgress = findViewById(R.id.subscriptionDetailListProgress);
        titleView.setText("Top Ranking");
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("TestID") != null){
            testId = bundle.getString("TestID");
            testMarks = bundle.getString("Test-Marks");
            testResult = bundle.getString("Test-Result");
        }

        leaderBoardAdapter = new LeaderBoardAdapter(this,leaderboardDataList);
        recyclerViewleaderBoard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewleaderBoard.setAdapter(leaderBoardAdapter);
        leaderboardAPI();
        backBtnView.setOnClickListener(view -> onBackPressed());
    }

    private void initUIData(){


    }

    private void leaderboardAPI(){
        System.out.println("Print the Result Data: "+testResult);
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.testResultOrLeaderboardData("Bearer "+ MyApplication.AUTH_TOKEN,testId,testMarks,testResult).enqueue(new Callback<ResoureData<TestResultData>>() {
            @Override
            public void onResponse(Call<ResoureData<TestResultData>> call, Response<ResoureData<TestResultData>> response) {
                subscriptionDetailListProgress.setVisibility(View.INVISIBLE);
                leaderboardDataList.addAll(response.body().getData().getLeaderboard());
                leaderBoardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResoureData<TestResultData>> call, Throwable t) {
                subscriptionDetailListProgress.setVisibility(View.INVISIBLE);
                System.out.println("Print Data Failed:");
            }
        });
    }
}
