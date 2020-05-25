package com.app.alphasucess.ui.tabui.home;

import android.content.Intent;
import android.os.Bundle;
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
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.home.adapter.BannerData;
import com.app.alphasucess.ui.tabui.home.adapter.HomeData;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseAdapter;
import com.app.alphasucess.ui.tabui.home.adapter.BannerViewAdaper;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseVideo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView_onlinecourse,recyclerView_onlineeducation,recyclerView_onlinevideo;
    private TextView txt_all_courses,txt_allExams,txt_allvideo;
    private LivecourseAdapter mAdapter;
    private BannerViewAdaper livecourseEdu;
    private LivecourseVideo livecourseVideo;
    private ArrayList<LiveData> liveCourseVideos = new ArrayList<>();
    private List<BannerData> bannerDataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        txt_all_courses = root.findViewById(R.id.txt_all_courses);
        txt_allvideo = root.findViewById(R.id.txt_allvideo);
        txt_allExams = root.findViewById(R.id.txt_allxams);
        recyclerView_onlinecourse = (RecyclerView) root.findViewById(R.id.rcy__livecourses);
        recyclerView_onlineeducation = (RecyclerView) root.findViewById(R.id.rcy_online_education);
        recyclerView_onlinevideo = (RecyclerView) root.findViewById(R.id.rcy_online_video);

        recyclerView_onlinevideo.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView_onlinecourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_onlineeducation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //set data and list adapter
        mAdapter = new LivecourseAdapter(getActivity());
        livecourseEdu = new BannerViewAdaper(getActivity(),bannerDataList);
        livecourseVideo = new LivecourseVideo(getActivity(),liveCourseVideos);
        recyclerView_onlinevideo.setAdapter(livecourseVideo);
        recyclerView_onlinecourse.setAdapter(mAdapter);
        recyclerView_onlineeducation.setAdapter(livecourseEdu);
        txt_all_courses.setOnClickListener(this);
        txt_allExams.setOnClickListener(this);
        txt_allvideo.setOnClickListener(this);
        initHomeDataListView();
        return root;
    }

    private void examListData(View root){
        TextView img_star = root.findViewById(R.id.img_star);
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

    @Override
    public void onClick(View view) {
        if(view == txt_all_courses){

            Intent viewAllLiveClasses = new Intent(getContext(), ViewAllLiveClassesActivity.class);
            getActivity().startActivity(viewAllLiveClasses);
        }else if(view == txt_allExams){


        }else if(view == txt_allvideo){

            
        }
    }

    private void initHomeDataListView(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.homeScreenDataList("Bearer "+ MyApplication.AUTH_TOKEN).enqueue(new Callback<ResoureData<HomeData>>() {
            @Override
            public void onResponse(Call<ResoureData<HomeData>> call, Response<ResoureData<HomeData>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {
                    Toast.makeText(getContext(),"Home screen data",Toast.LENGTH_LONG).show();
                    liveCourseVideos.addAll(response.body().getData().getVideos());
                    bannerDataList.addAll(response.body().getData().getHomebanners());
                    livecourseVideo.notifyDataSetChanged();
                    livecourseEdu.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<HomeData>> call, Throwable t) {

            }
        });
    }
}
