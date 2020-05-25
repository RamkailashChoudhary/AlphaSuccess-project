package com.app.alphasucess.ui.tabui.home.adapter;

import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;

import java.util.List;

public class HomeData {

    private List<LiveData> videos;
    private List<BannerData> homebanners;
    private List<ExamData> examcategory;

    public List<LiveData> getVideos() {
        return videos;
    }

    public void setVideos(List<LiveData> videos) {
        this.videos = videos;
    }

    public List<BannerData> getHomebanners() {
        return homebanners;
    }

    public void setHomebanners(List<BannerData> homebanners) {
        this.homebanners = homebanners;
    }

    public List<ExamData> getExamcategory() {
        return examcategory;
    }

    public void setExamcategory(List<ExamData> examcategory) {
        this.examcategory = examcategory;
    }
}
