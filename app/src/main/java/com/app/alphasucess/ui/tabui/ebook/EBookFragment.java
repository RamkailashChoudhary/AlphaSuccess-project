package com.app.alphasucess.ui.tabui.ebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.ebook.adapters.AutoFitGridLayoutManager;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EBookFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private EBookViewModel notificationsViewModel;
    private ArrayList<EbookData> ebookDataList = new ArrayList<>();
    private EbookRecyclerViewAdapter ebookRecyclerViewAdapter;
    private SwipeRefreshLayout swipeContainer;
    private int index = 1;
    private ProgressBar notificationLoaderView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(EBookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        initEbookView(recyclerView);
        notificationLoaderView = root.findViewById(R.id.notificationLoaderView);
        swipeContainer = (SwipeRefreshLayout) root.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        return root;
    }

    private void initEbookView(RecyclerView recyclerView){

        ebookRecyclerViewAdapter = new EbookRecyclerViewAdapter(getActivity(),ebookDataList);
        recyclerView.setAdapter(ebookRecyclerViewAdapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

       /* AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getActivity(), 500);
        recyclerView.setLayoutManager(layoutManager);*/
       ebookDataList();
    }

    private void ebookDataList(){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.ebookListData("Bearer "+ MyApplication.AUTH_TOKEN,""+index).enqueue(new Callback<ResoureData<List<EbookData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<EbookData>>> call, Response<ResoureData<List<EbookData>>> response) {
                notificationLoaderView.setVisibility(View.INVISIBLE);
               if(response.body() != null && response.body().getReplycode().equalsIgnoreCase("1")){

                   ebookDataList.addAll(response.body().getData());
                   ebookRecyclerViewAdapter.notifyDataSetChanged();
               }
            }

            @Override
            public void onFailure(Call<ResoureData<List<EbookData>>> call, Throwable t) {
                notificationLoaderView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onRefresh() {
        index++;
        ebookDataList();
        swipeContainer.setRefreshing(false);
    }
}
