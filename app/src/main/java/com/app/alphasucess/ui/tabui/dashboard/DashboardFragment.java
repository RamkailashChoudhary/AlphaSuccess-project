package com.app.alphasucess.ui.tabui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.TopBarClickEvent;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveDataAdapter;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private DashboardViewModel dashboardViewModel;
    private ArrayList<LiveData> liveDataList = new ArrayList<>();
    private LiveDataAdapter liveDataAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int INDEX = 1;
    private ArrayList<ExamData> examDataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewVideoPlayer);
        swipeRefreshLayout = root.findViewById(R.id.swipeContainerLive);
        swipeRefreshLayout.setOnRefreshListener(this);
        initLiveDataListView(recyclerView);
        return root;
    }

    private void initLiveDataListView(RecyclerView recyclerView){

        liveDataAdapter = new LiveDataAdapter(getContext(),liveDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(liveDataAdapter);
        initLiveDataList();
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
        Toast.makeText(getActivity(),"Clicked on live data "+event.getId(),Toast.LENGTH_LONG).show();};
    private void initLiveDataList(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.liveDataList("Bearer "+ MyApplication.AUTH_TOKEN,INDEX+"").enqueue(new Callback<ResoureData<List<LiveData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<LiveData>>> call, Response<ResoureData<List<LiveData>>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    liveDataList.add(0,new LiveData());
                    liveDataList.addAll(response.body().getData());
                    liveDataAdapter.notifyDataSetChanged();
                    examCategoryListData();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<List<LiveData>>> call, Throwable t) {

            }
        });
    }

    private void examCategoryListData(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.examCategoryList("Bearer "+ MyApplication.AUTH_TOKEN).enqueue(new Callback<ResoureData<List<ExamData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<ExamData>>> call, Response<ResoureData<List<ExamData>>> response) {

                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    examDataList.clear();
                    examDataList.addAll(response.body().getData());
                    liveDataAdapter.setExamCategoryData(examDataList);
                    liveDataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<List<ExamData>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        INDEX++;
        swipeRefreshLayout.setRefreshing(false);
        initLiveDataList();
    }
}
