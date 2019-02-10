package com.todo.test.app.controllers;

import com.todo.test.app.entities.Message;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Elimane on Feb, 2019, at 11:51
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class welcome {

    @GetMapping(value = "/message/{name}")
    public Message getMessage(@PathVariable String name)
    {
//        Message m = new Message();
//       m.setMessage("ME");
      // throw new RuntimeException("Error happened on the server please contact support *****");
        return new Message(String.format("Hello, %s", name));
    }


}
