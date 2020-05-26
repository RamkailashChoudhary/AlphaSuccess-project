package com.app.alphasucess.ui.tabui.home.adapter;

import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.home.ExamCategoryData;

import java.util.List;

public class HomeData {

    private List<LiveData> videos;
    private List<BannerData> homebanners;
    private List<ExamCategoryData> examcategory;
    private List<LiveClassData> liveclassdata;

    public List<LiveData> getVideos() {
        return videos;
    }

    public void setVideos(List<LiveData> videos) {
        this.videos = videos;
    }

    public List<BannerData> getHomebanners() {
        return homebanners;
    }

    public List<LiveClassData> getLiveclassdata() {
        return liveclassdata;
    }

    public void setLiveclassdata(List<LiveClassData> liveclassdata) {
        this.liveclassdata = liveclassdata;
    }

    public void setHomebanners(List<BannerData> homebanners) {
        this.homebanners = homebanners;
    }

    public List<ExamCategoryData> getExamcategory() {
        return examcategory;
    }

    public void setExamcategory(List<ExamCategoryData> examcategory) {
        this.examcategory = examcategory;
    }
}
