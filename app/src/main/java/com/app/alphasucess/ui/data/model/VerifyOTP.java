package com.app.alphasucess.ui.data.model;

public class VerifyOTP {

    private String replycode;
    private String message;
    private boolean isPhoneVerified;

    public String getReplycode() {
        return replycode;
    }

    public void setReplycode(String replycode) {
        this.replycode = replycode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }
}
