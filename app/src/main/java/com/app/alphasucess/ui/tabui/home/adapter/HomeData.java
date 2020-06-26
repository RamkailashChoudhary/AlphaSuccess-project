package com.app.alphasucess.ui.tabui.home.adapter;

import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.home.ExamCategoryData;

import java.util.List;

public class HomeData {

    private List<LiveData> videos;
    private List<BannerData> subscriptionbanners;
    private List<ExamCategoryData> examcategory;
    private List<LiveClassData> liveclassdata;

    public List<BannerData> getSubscriptionbanners() {
        return subscriptionbanners;
    }

    public void setSubscriptionbanners(List<BannerData> subscriptionbanners) {
        this.subscriptionbanners = subscriptionbanners;
    }

    public List<LiveData> getVideos() {
        return videos;
    }

    public void setVideos(List<LiveData> videos) {
        this.videos = videos;
    }

    public List<LiveClassData> getLiveclassdata() {
        return liveclassdata;
    }

    public void setLiveclassdata(List<LiveClassData> liveclassdata) {
        this.liveclassdata = liveclassdata;
    }

    public List<ExamCategoryData> getExamcategory() {
        return examcategory;
    }

    public void setExamcategory(List<ExamCategoryData> examcategory) {
        this.examcategory = examcategory;
    }
}
