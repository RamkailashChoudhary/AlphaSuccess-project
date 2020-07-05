package com.app.alphasucess.ui.tabui.adapter;

public class ExamData {

    private String id;
    private String testname;
    private String teachername;
    private String testtype;
    private String timeinminutes;
    private String totalmarks;
    private String questioncount;
    private String adddate;
    private String categoryname;
    private String colorcode;
    private String iconurl;
    private String examid;
    private String subjectid;
    private String subscriptionid;
    private String blockmessage;
    private boolean ispaid;
    private boolean levelcleared;
    private boolean openForView;
    private boolean issubscribed;
    private boolean reviewtest;

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(String subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public String getBlockmessage() {
        return blockmessage;
    }

    public void setBlockmessage(String blockmessage) {
        this.blockmessage = blockmessage;
    }

    public boolean isIspaid() {
        return ispaid;
    }

    public void setIspaid(boolean ispaid) {
        this.ispaid = ispaid;
    }

    public boolean isLevelcleared() {
        return levelcleared;
    }

    public void setLevelcleared(boolean levelcleared) {
        this.levelcleared = levelcleared;
    }

    public boolean isOpenForView() {
        return openForView;
    }

    public void setOpenForView(boolean openForView) {
        this.openForView = openForView;
    }

    public boolean isIssubscribed() {
        return issubscribed;
    }

    public void setIssubscribed(boolean issubscribed) {
        this.issubscribed = issubscribed;
    }

    public boolean isReviewtest() {
        return reviewtest;
    }

    public void setReviewtest(boolean reviewtest) {
        this.reviewtest = reviewtest;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;


    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

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

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }
}
