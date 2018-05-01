package com.socket.app.controllers;

import com.socket.app.hello.Greeting;
import com.socket.app.hello.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by Elimane on Dec, 2017, at 08:03
 */
public class GreetingController {

    @MessageMapping("/hello")//annotation that ensures that if a message is sent to destination "/hello", then the greeting() method is called
    @SendTo("/topic/greetings")//The return value is broadcast to all subscribers to "/topic/greetings" as specified in the @SendTo annotation
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay is to demonstrate that after the client sends a message, the server can take as long as it needs to process the message asynchronously
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
