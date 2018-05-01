package org.chuck.app.chuck.controllers;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.chuck.app.chuck.services.ChuckInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Elimane on Oct, 2017, at 18:59
 */
@Controller
public class ChuckController {

    @Autowired
    private  ChuckInterface service;


    @RequestMapping("/")
    public String getQuote(Model m)
    {
        m.addAttribute("quoteText",service.getQuote());
          return "chuckIndex";

    }
}
