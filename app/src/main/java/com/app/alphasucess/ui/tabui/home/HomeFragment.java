package com.app.alphasucess.ui.tabui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.test.adapters.QuestionCountAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final RecyclerView recyclerView = root.findViewById(R.id.examRecyclerView);
        initHomeExamView(recyclerView);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void initHomeExamView(RecyclerView recyclerView){
        ArrayList<ExamData> examDataList = new ArrayList<>();
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        examDataList.add(new ExamData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        ExamAdapter examAdapter = new ExamAdapter(getActivity(),examDataList);
        recyclerView.setAdapter(examAdapter);

    }
}
