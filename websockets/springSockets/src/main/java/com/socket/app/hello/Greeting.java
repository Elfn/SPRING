package com.socket.app.hello;

/**
 * Created by Elimane on Dec, 2017, at 07:52
 */
//greeting representation which will asscociated to name
public class Greeting {

    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    public Greeting() {
    }

    public String getContent() {
        return content;
    }
}
