package com.app.alphasucess.ui.tabui.home.adapter;

public class BannerData {

    private String id;
    private String bannerurl;
    private String subscription_id;
    private String bannertype;

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerurl() {
        return bannerurl;
    }

    public void setBannerurl(String bannerurl) {
        this.bannerurl = bannerurl;
    }

    public String getBannertype() {
        return bannertype;
    }

    public void setBannertype(String bannertype) {
        this.bannertype = bannertype;
    }
}
