package com.restclient.demo.rest_template_client.controllers;

import com.restclient.demo.rest_template_client.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

import java.time.Duration;

/**
 * Created by Elimane on May, 2018, at 07:20
 */
@Slf4j
@Controller
public class UserController {

    //getFormData()
   // Return the form data from the body of the request if the Content-Type is
    // "application/x-www-form-urlencoded" or an empty map otherwise.


    //ServerWebExchange Contract for an HTTP request-response interaction.
    // Provides access to the HTTP request and response and also exposes additional
    // server-side processing related properties and features such as request attributes.

    private ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange){

        //"MultiValueMap" Extension of the Map interface that stores multiple values.
        MultiValueMap<String, String> map = serverWebExchange.getFormData().block(Duration.ofMillis(5000));

        Integer limit = new Integer(map.get("limit").get(0));

        log.debug("Received Limit value: " + limit);
        //default if null or zero
        if(limit == null || limit == 0){
            log.debug("Setting limit to default of 10");
            limit = 10;
        }

        model.addAttribute("users", apiService.getUsers(limit));

        return "userlist";
    }

}
