package com.app.alphasucess;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.TopicModelData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadDataAdapter;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseVideo;
import com.app.alphasucess.ui.tabui.test.adapters.AllTestData;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicViewActivity extends BaseActivity{

    private RecyclerView recyclerViewVideos,recyclerViewTest,recyclerViewPdfView;
    private LivecourseVideo livecourseVideo;
    private ArrayList<LiveData> liveCourseVideosList = new ArrayList<>();

    private OnlineTestAdapter onlineTestAdapter;
    private ArrayList<AllTestData> testListData = new ArrayList<>();

    private DownloadDataAdapter downloadDataAdapter;
    private ArrayList<DownloadData> pdfListData = new ArrayList<>();

    private ProgressBar progressLoader;
    private ImageView backBtnView;
    private TextView middleTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topicview_activity);

        middleTitle = findViewById(R.id.middleTitle);
        backBtnView = findViewById(R.id.backBtnView);
        recyclerViewPdfView = findViewById(R.id.recyclerViewPdfView);
        recyclerViewTest = findViewById(R.id.recyclerViewTest);
        recyclerViewVideos = findViewById(R.id.recyclerViewVideos);
        progressLoader = findViewById(R.id.progressLoader);

        initUIView();

        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initUIView(){

        livecourseVideo = new LivecourseVideo(this,liveCourseVideosList);
        recyclerViewVideos.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewVideos.setAdapter(livecourseVideo);

        onlineTestAdapter = new OnlineTestAdapter(this,testListData);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTest.setAdapter(onlineTestAdapter);

        downloadDataAdapter = new DownloadDataAdapter(this,pdfListData);
        recyclerViewPdfView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewPdfView.setAdapter(downloadDataAdapter);

        relatedTopicDataList();
    }

    private void relatedTopicDataList(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.topicDataDetail("Bearer "+ MyApplication.AUTH_TOKEN,"1").enqueue(new Callback<ResoureData<TopicModelData>>() {
            @Override
            public void onResponse(Call<ResoureData<TopicModelData>> call, Response<ResoureData<TopicModelData>> response) {

                middleTitle.setText(response.body().getMessage());
                progressLoader.setVisibility(View.INVISIBLE);
                liveCourseVideosList.addAll(response.body().getData().getVideos());
                livecourseVideo.notifyDataSetChanged();

                testListData.addAll(response.body().getData().getTests());
                onlineTestAdapter.notifyDataSetChanged();

                pdfListData.addAll(response.body().getData().getPdfs());
                downloadDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResoureData<TopicModelData>> call, Throwable t) {
                progressLoader.setVisibility(View.INVISIBLE);
            }
        });
    }
}
