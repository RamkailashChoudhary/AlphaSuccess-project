package com.app.alphasucess.ui.tabui.download;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadDataAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DownloadFragment  extends Fragment {

    private DownloadViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DownloadViewModel.class);

        View root = inflater.inflate(R.layout.fragment_download, container, false);
        final TextView textView = root.findViewById(R.id.text_download);
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

        ArrayList<DownloadData> downloadDataList = new ArrayList<>();
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        downloadDataList.add(new DownloadData());
        DownloadDataAdapter downloadDataAdapter = new DownloadDataAdapter(getActivity(),downloadDataList);
        recyclerView.setAdapter(downloadDataAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }
}