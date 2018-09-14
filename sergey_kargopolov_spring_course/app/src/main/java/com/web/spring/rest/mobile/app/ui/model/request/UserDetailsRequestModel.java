package com.web.spring.rest.mobile.app.ui.model.request;

/**
 * Created by Elimane on Sep, 2018, at 02:59
 */

//Contaain user informations which will be submitted througth a form

public class UserDetailsRequestModel {
    //private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
