package com.app.alphasucess.ui.tabui.test.adapters;

import java.util.List;

public class SingleTestQuestion {

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

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTesttype() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype = testtype;
    }

    public String getTimeinminutes() {
        return timeinminutes;
    }

    public void setTimeinminutes(String timeinminutes) {
        this.timeinminutes = timeinminutes;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    public String getQuestioncount() {
        return questioncount;
    }

    public void setQuestioncount(String questioncount) {
        this.questioncount = questioncount;
    }

    public List<TestData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestData> questions) {
        this.questions = questions;
    }
}
