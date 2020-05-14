package com.app.alphasucess.ui.data.model;

import java.util.ArrayList;

public class StateResponse {
    private String message;
    private int replycode;
    private ArrayList<StateListData>data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReplycode() {
        return replycode;
    }

    public void setReplycode(int replycode) {
        this.replycode = replycode;
    }

    public ArrayList<StateListData> getData() {
        return data;
    }

    public void setData(ArrayList<StateListData> data) {
        this.data = data;
    }
}
