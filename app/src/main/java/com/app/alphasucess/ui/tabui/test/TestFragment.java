package com.app.alphasucess.ui.tabui.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.ebook.EBookViewModel;
import com.app.alphasucess.ui.tabui.test.adapters.AllTestData;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestAdapter;
import com.app.alphasucess.ui.tabui.test.adapters.TestData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestFragment extends Fragment {

    private TestViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_online_test, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.onlineTestRecyclerView);
        initTestRows(recyclerView);
        final TextView textView = root.findViewById(R.id.text_test);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void initTestRows(RecyclerView recyclerView){

        ArrayList<TestData> onLineTestData = new ArrayList<>();
        onLineTestData.add(new TestData());
        onLineTestData.add(new TestData());
        onLineTestData.add(new TestData());
        onLineTestData.add(new TestData());
        onLineTestData.add(new TestData());
        onLineTestData.add(new TestData());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        OnlineTestAdapter onlineTestAdapter = new OnlineTestAdapter(getContext(),onLineTestData);
        recyclerView.setAdapter(onlineTestAdapter);
        testListData();
    }

    private void testListData(){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.testListData("Bearer "+ MyApplication.AUTH_TOKEN,"1").enqueue(new Callback<ResoureData<List<AllTestData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<AllTestData>>> call, Response<ResoureData<List<AllTestData>>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {
                    Log.d("TestList","Size of the List Data :"+response.body().getData().size());
                    Toast.makeText(getContext(),"Message :"+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<List<AllTestData>>> call, Throwable t) {

            }
        });
    }
}

