package com.app.alphasucess.ui.tabui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseAdapter;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseEdu;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseVideo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView_onlinecourse,recyclerView_onlineeducation,recyclerView_onlinevideo;
    private TextView txt_all_courses,txt_allExams;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        final RecyclerView recyclerView = root.findViewById(R.id.examRecyclerView);
//        initHomeExamView(recyclerView);
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        txt_all_courses = root.findViewById(R.id.txt_all_courses);
        txt_allExams = root.findViewById(R.id.txt_allxams);
        recyclerView_onlinecourse = (RecyclerView) root.findViewById(R.id.rcy__livecourses);
        recyclerView_onlineeducation = (RecyclerView) root.findViewById(R.id.rcy_online_education);
        recyclerView_onlinevideo = (RecyclerView) root.findViewById(R.id.rcy_online_video);


        recyclerView_onlinevideo.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        recyclerView_onlinecourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        recyclerView_onlineeducation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



        //set data and list adapter
        LivecourseAdapter mAdapter = new LivecourseAdapter(getActivity());
        LivecourseEdu livecourseEdu = new LivecourseEdu(getActivity());
        LivecourseVideo livecourseVideo = new LivecourseVideo(getActivity());
        recyclerView_onlinevideo.setAdapter(livecourseVideo);
        recyclerView_onlinecourse.setAdapter(mAdapter);
        recyclerView_onlineeducation.setAdapter(livecourseEdu);
        txt_all_courses.setOnClickListener(this);
        txt_allExams.setOnClickListener(this);
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

    private void examListData(){

    }

    @Override
    public void onClick(View view) {
        if(view == txt_all_courses){

            Intent viewAllLiveClasses = new Intent(getContext(), ViewAllLiveClassesActivity.class);
            getActivity().startActivity(viewAllLiveClasses);
        }else if(view == txt_allExams){


        }
    }
}
