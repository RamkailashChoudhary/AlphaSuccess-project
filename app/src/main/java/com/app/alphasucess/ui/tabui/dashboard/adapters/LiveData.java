package com.app.alphasucess.ui.tabui.dashboard.adapters;

public class LiveData {

    private String id;
    private String title;
    private String thumbnailurl;
    private String teachername;
    private int views;
    private String videourl;
    private String subscriptionid;
    private String examid;
    private String subjectid;
    private boolean isPaid;
    private int level;
    private boolean openForView;
    private String blockmessage;
    private boolean issubscribed;

    public String getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(String subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isOpenForView() {
        return openForView;
    }

    public void setOpenForView(boolean openForView) {
        this.openForView = openForView;
    }

    public String getBlockmessage() {
        return blockmessage;
    }

    public void setBlockmessage(String blockmessage) {
        this.blockmessage = blockmessage;
    }

    public boolean isIssubscribed() {
        return issubscribed;
    }

    public void setIssubscribed(boolean issubscribed) {
        this.issubscribed = issubscribed;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
