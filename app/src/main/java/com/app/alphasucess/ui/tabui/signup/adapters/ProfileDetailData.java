package com.app.alphasucess.ui.tabui.signup.adapters;

public class ProfileDetailData {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String address;
    private int stateid;
    private String referralid;
    private String otp;
    private boolean isReffered;
    private String referredby;
    private String referralpoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public String getReferralid() {
        return referralid;
    }

    public void setReferralid(String referralid) {
        this.referralid = referralid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isReffered() {
        return isReffered;
    }

    public void setReffered(boolean reffered) {
        isReffered = reffered;
    }

    public String getReferredby() {
        return referredby;
    }

    public void setReferredby(String referredby) {
        this.referredby = referredby;
    }

    public String getReferralpoints() {
        return referralpoints;
    }

    public void setReferralpoints(String referralpoints) {
        this.referralpoints = referralpoints;
    }
}
