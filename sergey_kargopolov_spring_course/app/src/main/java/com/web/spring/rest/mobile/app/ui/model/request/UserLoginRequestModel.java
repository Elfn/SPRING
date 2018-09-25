package com.web.spring.rest.mobile.app.ui.model.request;

/**
 * Created by Elimane on Sep, 2018, at 16:11
 */
public class UserLoginRequestModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
