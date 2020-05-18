package com.app.alphasucess.ui.tabui.test;

import com.app.alphasucess.ui.tabui.test.adapters.TestData;

import java.util.List;

public class TestResponseData {
    private String id;
    private String testname;
    private String teachername;
    private String testtype;
    private String timeinminutes;
    private String totalmarks;
    private String questioncount;
    private List<TestData> questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
