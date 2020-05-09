package com.app.alphasucess.ui.tabui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.ebook.EBookViewModel;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestAdapter;
import com.app.alphasucess.ui.tabui.test.adapters.TestData;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    }
}

