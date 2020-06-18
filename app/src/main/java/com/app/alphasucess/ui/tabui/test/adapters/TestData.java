package com.app.alphasucess.ui.tabui.test.adapters;

import java.util.List;

public class TestData {

    private String id;
    private String testid;
    private String testquestion;
    private String explaination;
    private int answerData;
    private List<OptionData> options;

    public int getAnswerData() {
        return answerData;
    }

    public void setAnswerData(int answerData) {
        this.answerData = answerData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTestquestion() {
        return testquestion;
    }

    public void setTestquestion(String testquestion) {
        this.testquestion = testquestion;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public List<OptionData> getOptions() {
        return options;
    }

    public void setOptions(List<OptionData> options) {
        this.options = options;
    }
}
