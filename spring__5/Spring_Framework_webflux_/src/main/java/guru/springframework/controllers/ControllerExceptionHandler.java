package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
//import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jt on 7/14/17.
 */
@Slf4j
@ControllerAdvice
//@ControllerAdvice used for global error handling in the Spring MVC application.
// It also has full control over the body of the response and the status code.
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)//WebExchangeBindException class is a specialization of ServerWebInputException thrown when after data binding and validation failure. Implements BindingResult (and its super-interface Errors) to allow for direct analysis of binding and validation errors.
    public String handleNumberFormat(Exception exception, Model model){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

       // ModelAndView modelAndView = new ModelAndView();

        //modelAndView.setViewName("400error");
        model.addAttribute("exception", exception);

        return "400error";
    }
}
