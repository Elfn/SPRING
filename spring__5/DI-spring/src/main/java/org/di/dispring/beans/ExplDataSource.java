package org.di.dispring.beans;

import org.springframework.stereotype.Component;

/**
 * Created by Elimane on Oct, 2017, at 13:09
 */
public class ExplDataSource {

    private String username;
    private String password;

    public ExplDataSource() {
    }

    public ExplDataSource(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
