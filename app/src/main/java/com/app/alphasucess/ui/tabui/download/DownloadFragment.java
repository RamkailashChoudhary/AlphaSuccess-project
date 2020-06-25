package com.app.alphasucess.ui.tabui.download;

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
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadDataAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadFragment  extends Fragment {

    private DownloadViewModel dashboardViewModel;
    private ArrayList<DownloadData> downloadDataList = new ArrayList<>();
    private DownloadDataAdapter downloadDataAdapter;
    private ProgressBar downloadLoaderView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DownloadViewModel.class);

        View root = inflater.inflate(R.layout.fragment_download, container, false);
        final TextView textView = root.findViewById(R.id.text_download);
        downloadLoaderView = root.findViewById(R.id.downloadLoaderView);
        final RecyclerView recyclerView = root.findViewById(R.id.downloadRecyclerView);
        initDownloadView(recyclerView);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void initDownloadView(RecyclerView recyclerView){

        downloadDataAdapter = new DownloadDataAdapter(getActivity(),downloadDataList);
        recyclerView.setAdapter(downloadDataAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        initPdfDataList();
    }

    private void initPdfDataList(){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN);
        restServiceLayer.pdfListData("Bearer "+MyApplication.AUTH_TOKEN,"1","0","0").enqueue(new Callback<ResoureData<List<DownloadData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<DownloadData>>> call, Response<ResoureData<List<DownloadData>>> response) {
                downloadLoaderView.setVisibility(View.INVISIBLE);
                if (response.body().getReplycode().equals("1")) {
                  downloadDataList.addAll(response.body().getData());
                  downloadDataAdapter.notifyDataSetChanged();
              }
            }

            @Override
            public void onFailure(Call<ResoureData<List<DownloadData>>> call, Throwable t) {
                downloadLoaderView.setVisibility(View.INVISIBLE);
            }
        });
    }
}