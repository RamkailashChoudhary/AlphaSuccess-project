package com.app.alphasucess.ui.data.model;

import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.test.adapters.AllTestData;

import java.util.List;

public class TopicModelData {

    private String id;
    private String topicname;
    private List<LiveData> videos;
    private List<AllTestData> tests;
    private List<DownloadData> pdfs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public List<LiveData> getVideos() {
        return videos;
    }

    public void setVideos(List<LiveData> videos) {
        this.videos = videos;
    }

    public List<AllTestData> getTests() {
        return tests;
    }

    public void setTests(List<AllTestData> tests) {
        this.tests = tests;
    }

    public List<DownloadData> getPdfs() {
        return pdfs;
    }

    public void setPdfs(List<DownloadData> pdfs) {
        this.pdfs = pdfs;
    }
}
