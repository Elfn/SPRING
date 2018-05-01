package com.rcp.recipe.controllers;

import com.rcp.recipe.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Elimane on Apr, 2018, at 07:59
 */

//Enable to handle exceptions types globally
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    //Thrown to indicate that the application has attempted
    // to convert a string to one of the numeric types,
    // but that the string does not have the appropriate format.

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    //" ModelAndView" Holder for both Model and View in the web MVC framework.
    // Note that these are entirely distinct.
    // This class merely holds both to make it possible
    // for a controller to return both model and view in a single return value
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");

        modelAndView.addObject("exception",exception);

        return modelAndView;
    }

}
