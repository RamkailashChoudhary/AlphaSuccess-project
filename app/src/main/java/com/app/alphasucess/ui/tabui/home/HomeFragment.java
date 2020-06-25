package com.app.alphasucess.ui.tabui.home;

import android.content.Intent;
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
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.home.adapter.BannerData;
import com.app.alphasucess.ui.tabui.home.adapter.ExamCategoryAdapter;
import com.app.alphasucess.ui.tabui.home.adapter.HomeData;
import com.app.alphasucess.ui.tabui.home.adapter.LiveClassData;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView_onlinecourse,recyclerView_onlineeducation,recyclerView_onlinevideo,rec_live_courses;
    private TextView txt_all_courses,txt_allExams,txt_allvideo;
    private LivecourseAdapter mAdapter;
    private ExamCategoryAdapter examCategoryAdapter;
    private BannerViewAdaper livecourseEdu;
    private LivecourseVideo livecourseVideo;
    private ArrayList<LiveData> liveCourseVideos = new ArrayList<>();
    private ArrayList<ExamCategoryData> examCategoryDataArrayList = new ArrayList<>();
    private ArrayList<LiveClassData> liveCourselist = new ArrayList<>();
    private List<BannerData> bannerDataList = new ArrayList<>();
    private ProgressBar progressLoader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressLoader = root.findViewById(R.id.progressLoader);
        txt_all_courses = root.findViewById(R.id.txt_all_courses);
        txt_allvideo = root.findViewById(R.id.txt_allvideo);
        txt_allExams = root.findViewById(R.id.txt_allxams);
        recyclerView_onlinecourse = (RecyclerView) root.findViewById(R.id.rcy__livecourses);
        recyclerView_onlineeducation = (RecyclerView) root.findViewById(R.id.rcy_online_education);
        recyclerView_onlinevideo = (RecyclerView) root.findViewById(R.id.rcy_online_video);
        rec_live_courses = (RecyclerView) root.findViewById(R.id.rec_live_courses);

        recyclerView_onlinevideo.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rec_live_courses.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView_onlinecourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_onlineeducation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //set data and list adapter
        mAdapter = new LivecourseAdapter(getActivity(),liveCourselist);
        livecourseEdu = new BannerViewAdaper(getActivity(),bannerDataList);
        examCategoryAdapter = new ExamCategoryAdapter(getActivity(),examCategoryDataArrayList);
        livecourseVideo = new LivecourseVideo(getActivity(),liveCourseVideos);
        recyclerView_onlinevideo.setAdapter(livecourseVideo);
        rec_live_courses.setAdapter(examCategoryAdapter);
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

          Toast.makeText(getActivity(),"Exam view all",Toast.LENGTH_LONG).show();
        }else if(view == txt_allvideo){

            Toast.makeText(getActivity(),"Video view all",Toast.LENGTH_LONG).show();
        }
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
    public void onMessageEvent(TopBarClickEvent event) {
        /* Do something */
        if(event.getId().equalsIgnoreCase("-1")){
            progressLoader.setVisibility(View.VISIBLE);
        }else {
            progressLoader.setVisibility(View.INVISIBLE);
        }
    };

    private void initHomeDataListView(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.homeScreenDataList("Bearer "+ MyApplication.AUTH_TOKEN).enqueue(new Callback<ResoureData<HomeData>>() {
            @Override
            public void onResponse(Call<ResoureData<HomeData>> call, Response<ResoureData<HomeData>> response) {
                progressLoader.setVisibility(View.INVISIBLE);
                if(response.body().getReplycode().equalsIgnoreCase("1")) {
                    liveCourseVideos.addAll(response.body().getData().getVideos());
                    bannerDataList.addAll(response.body().getData().getSubscriptionbanners());
                    liveCourselist.addAll(response.body().getData().getLiveclassdata());
                    examCategoryDataArrayList.addAll(response.body().getData().getExamcategory());
                    livecourseVideo.notifyDataSetChanged();
                    mAdapter.notifyDataSetChanged();
                    examCategoryAdapter.notifyDataSetChanged();
                    livecourseEdu.notifyDataSetChanged();
                }
            }


            @Override
            public void onFailure(Call<ResoureData<HomeData>> call, Throwable t) {
                progressLoader.setVisibility(View.INVISIBLE);
            }
        });
    }
}
