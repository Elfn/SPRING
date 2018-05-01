package org.di.dispring.controllers;

import org.di.dispring.services.GreetingService;
import org.di.dispring.services.TextProcess;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {


    private GreetingService service;

    public MyController(GreetingService service) {
        this.service = service;
    }

    public String greeting()
    {
        return service.sayGreeting();
    }

    // TextProcess service;

//    public MyController(TextProcess service) {
//        this.service = service;
//    }

//    public String great(String input)
//    {
//        //System.out.println("Hello world!!!");
//
//        return service.removeAInFirst2Positions(input);
//    }

}
