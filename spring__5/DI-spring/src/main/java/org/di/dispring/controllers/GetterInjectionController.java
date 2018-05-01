package org.di.dispring.controllers;

import org.di.dispring.services.TextProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/***
 *
 * @Qualifier is used to define which implementation to execute in a controller
 * */
@Controller
public class GetterInjectionController {

    @Autowired
    @Qualifier("textProcessImpl")
    private TextProcess process;


    public void setProcess(TextProcess process) {
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
