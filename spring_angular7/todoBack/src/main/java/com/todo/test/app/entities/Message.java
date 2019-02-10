package com.todo.test.app.entities;

/**
 * Created by Elimane on Feb, 2019, at 12:04
 */

public class Message {

    private String message = "HEllo";


    public String getMessage() {
        return message;
    }

    public Message(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
