package com.app.alphasucess.ui.tabui.login;

public class LoginResponse {
    private String access_token;
    private String id;
    private String Name;
    private String expires_in;
    private String token_type;
    private String ReplyCode;
    private String message;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getReplyCode() {
        return ReplyCode;
    }

    public void setReplyCode(String replyCode) {
        ReplyCode = replyCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
