package com.app.alphasucess.ui.tabui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.TopBarClickEvent;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.test.adapters.AllTestData;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestAdapter;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestFragment extends Fragment {

    private TestViewModel notificationsViewModel;
    private ArrayList<AllTestData> onLineTestData = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArrayList<ExamData> examCategoryList = new ArrayList<>();
    private ProgressBar testProgress;
    private String examId = "0";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_online_test, container, false);
        recyclerView = root.findViewById(R.id.onlineTestRecyclerView);
        testProgress = root.findViewById(R.id.testProgress);
        testProgress.setVisibility(View.VISIBLE);
        initTestRows(recyclerView);

        return root;
    }

    private  OnlineTestAdapter onlineTestAdapter;

    private void initTestRows(RecyclerView recyclerView){

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        onlineTestAdapter = new OnlineTestAdapter(getContext(),onLineTestData);
        recyclerView.setAdapter(onlineTestAdapter);
        testListData();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TopBarClickEvent event) {/* Do something */
        testProgress.setVisibility(View.VISIBLE);
        examId = event.getId();
        onLineTestData.clear();
        testListData();
    };
    private void testListData(){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,getContext());
        restServiceLayer.testListData("Bearer "+ MyApplication.AUTH_TOKEN,examId,"1").enqueue(new Callback<ResoureData<List<AllTestData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<AllTestData>>> call, Response<ResoureData<List<AllTestData>>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    testProgress.setVisibility(View.INVISIBLE);
                    onLineTestData.addAll(response.body().getData());
                    onLineTestData.add(0,new AllTestData());
                    onlineTestAdapter.notifyDataSetChanged();
                    examCategoryListData();
//                    Toast.makeText(getContext(),"Message :"+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<List<AllTestData>>> call, Throwable t) {
                testProgress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void examCategoryListData(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,getContext());
        restServiceLayer.examCategoryList("Bearer "+ MyApplication.AUTH_TOKEN).enqueue(new Callback<ResoureData<List<ExamData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<ExamData>>> call, Response<ResoureData<List<ExamData>>> response) {

                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    examCategoryList.clear();
                    examCategoryList.addAll(response.body().getData());
                    onlineTestAdapter.setExamCategoryDataList(examCategoryList);
                    onlineTestAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<List<ExamData>>> call, Throwable t) {

            }
         });
    }
}

