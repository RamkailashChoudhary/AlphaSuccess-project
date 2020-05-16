package com.app.alphasucess.ui.data.model;

public class ResoureData<T> {

  private String replycode;
  private String message;
  private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
