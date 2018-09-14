package com.web.spring.rest.mobile.app.ui.model.response;

/**
 * Created by Elimane on Sep, 2018, at 03:09
 */

//Contaain user informations which will be sent back in the response

public class UserRest {

    //We don't want user password to be returned or other sensitive information

   // private String userID;
    private String firstName;
    private String lastName;
    private String password;
    private String email;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
