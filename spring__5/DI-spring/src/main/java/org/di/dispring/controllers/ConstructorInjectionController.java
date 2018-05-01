package org.di.dispring.controllers;

import org.di.dispring.services.TextProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectionController {

    @Autowired
    private TextProcess process;

    public ConstructorInjectionController(TextProcess process) {
        this.process = process;
    }

    public boolean isPalindrome(String text)
    {
       return  process.isPalindrome(text);
    }

    public String Aposition(String text)
    {
        return process.removeAInFirst2Positions(text);
    }
}
